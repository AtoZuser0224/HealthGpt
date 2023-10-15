package uni.server.healthgpt.service.impl;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uni.server.healthgpt.data.response.CommonResult;
import uni.server.healthgpt.data.response.ListResult;
import uni.server.healthgpt.data.response.SingleResult;

import java.util.List;

@Service
public class ResponseService {
    @Getter
    @RequiredArgsConstructor
    @AllArgsConstructor
    private enum CommonResponse {
        SUCCESS(0, "성공하였습니다."),
        FAIL(-1, "실패하였습니다.");
        private int code;
        private String message;
    }
    private void setSuccessResult(CommonResult commonResult) {
        commonResult.setSuccess(true);
        commonResult.setCode(CommonResponse.SUCCESS.getCode());
        commonResult.setMsg(CommonResponse.SUCCESS.getMessage());
    }


    public CommonResult getSuccessResult() {
        CommonResult commonResult = new CommonResult();
        setSuccessResult(commonResult);
        return commonResult;
    }

    public CommonResult getFailResult(int code, String message) {
        CommonResult commonResult = new CommonResult();
        commonResult.setSuccess(false);
        commonResult.setCode(code);
        commonResult.setMsg(message);
        return commonResult;
    }

    public <T> SingleResult<T> getSingleResult(T data) {
        SingleResult<T> singleResult = new SingleResult<>();
        singleResult.setData(data);
        setSuccessResult(singleResult);
        return singleResult;
    }
    public <T> ListResult<T> getListResult(List<T> list) {
        ListResult<T> listResult = new ListResult<>();
        listResult.setList(list);
        setSuccessResult(listResult);
        return listResult;
    }
}