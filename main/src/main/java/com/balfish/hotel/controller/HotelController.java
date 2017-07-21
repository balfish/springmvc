package com.balfish.hotel.controller;

import com.balfish.common.ApiResult;
import com.balfish.hotel.biz.HotelBiz;
import com.balfish.hotel.train.eventbus.MyEventBus;
import com.balfish.hotel.interceptor.Monitor;
import com.balfish.hotel.train.pipehandler.HandlerPipeline;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.RateLimiter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by yhm on 2017/7/12 PM2:17
 */
@Controller
@RequestMapping("hotel")
public class HotelController {

    @Resource
    private HotelBiz hotelBiz;

    //http://localhost:8080/balfish/hotel/test
    @RequestMapping(value = "test", method = RequestMethod.GET)
    @ResponseBody
    public String test() {
        return "testString";
    }

    //http://localhost:8080/balfish/hotel/modelAndView
    @RequestMapping(value = "modelAndView", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView modelAndView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("host", "www.baidu.com");
        modelAndView.setViewName("site");
        return modelAndView;
    }

    //http://localhost:8080/balfish/hotel/query?id=1
    @RequestMapping(value = "query")
    @ResponseBody
    public ApiResult query(@RequestParam(value = "id", required = true) Integer id) {
        return ApiResult.buildSuccessResult(hotelBiz.query(id));
    }


    //http://localhost:8080/balfish/hotel/addnull
    @RequestMapping(value = "addnull")
    @ResponseBody
    public ApiResult addnull() {
        hotelBiz.add(null);
        return ApiResult.buildSuccessResult();
    }

    //http://localhost:8080/balfish/hotel/event
    @RequestMapping(value = "event")
    @ResponseBody
    public ApiResult event() {
        MyEventBus.post(new Object());
        return ApiResult.buildSuccessResult();
    }

    //http://localhost:8080/balfish/hotel/handler   result:{"ver":"1.0","status":0,"data":"aa1122cc33"}
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

    //http://localhost:8080/balfish/hotel/monitor　这个需要ｘ掉全局的异常处理
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

    private static RateLimiter rateLimiter = RateLimiter.create(0.1);

    //http://localhost:8080/balfish/hotel/rateLimitter　
    @RequestMapping(value = "rateLimitter")
    @ResponseBody
    public ApiResult rateLimiter() {
        if (!rateLimiter.tryAcquire()) {
            return ApiResult.buildFailedResult("繁忙, 请稍候");
        }
        return ApiResult.buildSuccessResult("请求成功！");
    }
}
