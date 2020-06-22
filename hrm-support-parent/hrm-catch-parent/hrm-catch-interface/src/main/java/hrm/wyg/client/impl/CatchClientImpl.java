package hrm.wyg.client.impl;

import hrm.wyg.client.CatchClient;
import hrm.wyg.util.AjaxResult;
import org.springframework.stereotype.Component;

@Component
public class CatchClientImpl implements CatchClient {
    @Override
    public AjaxResult set(String key, String value) {
        return AjaxResult.me().setSuccess(false).setMessage("系统异常");
    }

    @Override
    public AjaxResult get(String key) {
        return AjaxResult.me().setSuccess(false).setMessage("系统异常");
    }

    @Override
    public AjaxResult setex(String key, Integer seconds, String value) {
        return AjaxResult.me().setSuccess(false).setMessage("系统异常");
    }

    @Override
    public AjaxResult setnx(String key, String value) {
        return AjaxResult.me().setSuccess(false).setMessage("系统异常");
    }

    @Override
    public AjaxResult deleteKey(String key) {
        return AjaxResult.me().setSuccess(false).setMessage("系统异常");
    }
}
