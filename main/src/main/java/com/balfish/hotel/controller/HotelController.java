package com.balfish.hotel.controller;

import com.balfish.common.ApiResult;
import com.balfish.hotel.biz.HotelBiz;
import com.balfish.hotel.dao.HotelDao;
import com.balfish.hotel.model.HotelEntity;
import com.balfish.hotel.train.eventbus.MyEventBus;
import com.balfish.hotel.interceptor.Monitor;
import com.balfish.hotel.train.pipehandler.HandlerPipeline;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.RateLimiter;
import javafx.util.Pair;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by yhm on 2017/7/12 PM2:17
 */
@Controller
@RequestMapping("hotel")
public class HotelController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HotelController.class);

    @Resource
    private HotelBiz hotelBiz;

    @Resource
    private HotelDao hotelDao;

    //http://localhost:8080/hotel/test
    @RequestMapping(value = "test", method = RequestMethod.GET)
    @ResponseBody
    public String test() {
        LOGGER.info("http://localhost:8080/hotel/test...");
        return "testString";
    }

    //http://localhost:8080/hotel/modelAndView
    @RequestMapping(value = "modelAndView", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView modelAndView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("host", "www.baidu.com");
        modelAndView.setViewName("site");
        return modelAndView;
    }

    //http://localhost:8080/hotel/query?id=1
    @RequestMapping(value = "query")
    @ResponseBody
    public ApiResult query(@RequestParam(value = "id", required = true) Integer id) {
        List<HotelEntity> hotelEntities = hotelDao.queryIds(Arrays.asList(4, 6, 7));
        hotelBiz.query(id);
        return ApiResult.buildSuccessResult(hotelEntities);
    }


    //http://localhost:8080/hotel/addnull
    @RequestMapping(value = "addnull")
    @ResponseBody
    public ApiResult addnull() {
        hotelBiz.add(null);
        return ApiResult.buildSuccessResult();
    }

    //http://localhost:8080/hotel/event
    @RequestMapping(value = "event")
    @ResponseBody
    public ApiResult event() {
        MyEventBus.post(new Object());
        return ApiResult.buildSuccessResult();
    }

    //http://localhost:8080/hotel/handler   result:{"ver":"1.0","status":0,"data":"aa1122cc33"}
    @Resource
    private HandlerPipeline handlerPipeline;

    @RequestMapping(value = "handler")
    @ResponseBody
    public ApiResult handler() {
        Map<String, Object> param = Maps.newHashMap();
        param.put("keyword", "BB");
        String s = handlerPipeline.doHandle("AA一一BB二二CC三三", param);
        return ApiResult.buildSuccessResult(s);
    }

    //http://localhost:8080/hotel/monitor　这个需要ｘ掉全局的异常处理
    @RequestMapping(value = "monitor")
    @ResponseBody
    @Monitor(onSucc = "succeed", onFail = "failed")
    public ApiResult monitor() throws Exception {
        int i = 1 + 1;
        if (i == 2) {
            throw new NullPointerException("空指针");
        }
        return ApiResult.buildSuccessResult(i);
    }

    private static RateLimiter rateLimiter = RateLimiter.create(0.2);

    //http://localhost:8080/hotel/rateLimitter　
    @RequestMapping(value = "rateLimitter")
    @ResponseBody
    public ApiResult rateLimiter() {
        if (!rateLimiter.tryAcquire()) {
            return ApiResult.buildFailedResult("繁忙, 请稍候");
        }
        return ApiResult.buildSuccessResult("请求成功！");
    }

    @RequestMapping(value = "testt/{type}", method = RequestMethod.GET)
    @ResponseBody
    public String test(@RequestParam(required = false) String type) {
        String type1 = type;
        LOGGER.info("http://localhost:8080/hotel/test...");
        return "testString";
    }

    @RequestMapping(value = "test11", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<List<String>> test11(@RequestParam("list") List<String> list) {
        LOGGER.info("http://localhost:8080/hotel/test...");
        list.add(list.get(1) + "手术室");
        return ApiResult.buildSuccessResult(list);
    }

    //http://localhost:8080/hotel/tx
    @RequestMapping(value = "tx")
    @ResponseBody
    public ApiResult tx(HttpServletRequest httpServletRequest) {

        try {
            hotelBiz.addTx();
            int i = 1 / 0;
        } catch (Exception e) {
            LOGGER.error("e = {}", e);
        }
        return ApiResult.buildSuccessResult(httpServletRequest.getRemoteAddr());
    }


    //http://localhost:8080/hotel/eb/redirect?continue=http://www.meituan1.com?param1=value1&param2=value2
    @RequestMapping(value = "/eb/redirect")
    public ResponseEntity redirect(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        if (1 == 1)
        return new ResponseEntity(HttpStatus.FORBIDDEN);

        String url = httpServletRequest.getParameter("continue");

        StringBuilder paramStr = new StringBuilder();
        Map parameterMap = httpServletRequest.getParameterMap();


        Cookie[] cookies = httpServletRequest.getCookies();
        for (Cookie cookie: cookies) {
            if (StringUtils.equals("ebbsid", cookie.getName())){
                url += "&" +  cookie.getName() + cookie.getValue();
                break;
            }
        }



        try {
            httpServletResponse.sendRedirect(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        double a = 23.36;

        String b = String.valueOf(a);

        String d = b.substring(0, b.lastIndexOf("."));
        System.out.println(d);

    }
}
