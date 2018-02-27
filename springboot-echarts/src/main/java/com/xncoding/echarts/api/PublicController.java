package com.xncoding.echarts.api;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.xncoding.echarts.api.model.BaseResponse;
import com.xncoding.echarts.api.model.EchartsData;
import com.xncoding.echarts.api.model.PicRequest;
import com.xncoding.echarts.service.ApiService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;

/**
 * 对外API接口类
 */
@RestController
@RequestMapping(value = "/api/v1")
public class PublicController {

    @Resource
    private ApiService apiService;

    private static final Logger _logger = LoggerFactory.getLogger(PublicController.class);

    /**
     * 数据上传接口，body直接接受字符串，不要转
     * @return 结果
     *
    {
        "title": {
            "text": "天气预报"
        },
        "tooltip": {
            "trigger": "axis",
            "axisPointer": {
                "type": "shadow"
            }
        },
        "legend": {
            "data": ["City Alpha", "City Beta", "City Gamma"]
        },
        "grid": {
            "left": 100
        },
        "toolbox": {
            "show": false,
            "feature": {
                "saveAsImage": {}
            }
        },
        "xAxis": {
            "type": "value",
            "name": "Days"
        },
        "yAxis": {
            "type": "category",
            "inverse": false,
            "data": ["Sunny", "Cloudy", "Showers"],
            "axisLabel": {
                "margin": 20
            }
        },
        "series": [
            {
                "name": "City Alpha",
                "type": "bar",
                "label": {
                    "normal": {
                        "show": true,
                        "textBorderWidth": 2
                    }
                },
                "data": [165, 170, 30]
            },
            {
                "name": "City Beta",
                "type": "bar",
                "label": {
                    "normal": {
                        "show": true,
                        "textBorderWidth": 2
                    }
                },
                "data": [150, 105, 110]
            },
            {
                "name": "City Gamma",
                "type": "bar",
                "label": {
                    "normal": {
                        "show": true,
                        "textBorderWidth": 2
                    }
                },
                "data": [220, 82, 63]
            }
        ]
    }
     */
    @RequestMapping(value = "/data", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> doJoin(HttpServletRequest request) throws Exception {
        _logger.info("数据上传消息push接口 start....");
        String jsonBody = IOUtils.toString(request.getInputStream(), Charset.forName("UTF-8"));
        EchartsData echartsData = new EchartsData("", jsonBody);
        String jsonString = new ObjectMapper().writeValueAsString(echartsData);
        apiService.pushMsg("notify", jsonString);
        BaseResponse result = new BaseResponse<>(true, "数据上传消息push成功", null);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

//    @RequestMapping(value = "/ask", method = RequestMethod.POST)
//    public ResponseEntity<BaseResponse> doAsk() {
//        ResponseEntity<BaseResponse> result;
//        String jsonString = apiService.popJson();
//        if (jsonString == null) {
//            result = new ResponseEntity<>(new BaseResponse<>(false, "轮询没有查到数据", null), HttpStatus.OK);
//        } else {
//            _logger.info("轮询后查询到数据");
//            result = new ResponseEntity<>(new BaseResponse<>(true, "轮询后查询到数据", jsonString), HttpStatus.OK);
//        }
//        return result;
//    }
//
//    /**
//     * 保存客户端传来的图片数据
//     *
//     * @param picInfo 图片BASE64
//     */
//    @RequestMapping(value = "/savePic", method = RequestMethod.POST)
//    public ResponseEntity<BaseResponse> onSavePic(@RequestParam("picInfo") String picInfo) {
//        _logger.info("保存客户端传来的图片数据 start");
//        String r = apiService.saveBase64Pic(picInfo);
//        _logger.info("保存客户端传来的图片 = {}", r);
//        return  new ResponseEntity<>(new BaseResponse<>(true, "图片保存成功", null), HttpStatus.OK);
//    }
}
