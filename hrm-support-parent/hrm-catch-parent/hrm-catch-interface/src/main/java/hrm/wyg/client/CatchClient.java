package hrm.wyg.client;

import hrm.wyg.client.impl.CatchClientImpl;
import hrm.wyg.util.AjaxResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
@FeignClient(name="cache-service",fallback = CatchClientImpl.class)
public interface CatchClient {
    /**
     * 设置字符值
     * @param key
     * @param value
     * @return
     */
    @GetMapping("/setStr")
    AjaxResult set(
            @RequestParam("key") String key, @RequestParam("value") String value);


    /**
     * 获取字符值
     * @param key
     * @return
     */
    @GetMapping("/getStr")
    AjaxResult get(@RequestParam("key")String key);

    @GetMapping("/setex")
    AjaxResult setex(
            @RequestParam("key") String key,
            @RequestParam("seconds")Integer seconds,
            @RequestParam("value") String value);


    @GetMapping("/setnx")
    AjaxResult setnx(
            @RequestParam("key") String key,
            @RequestParam("value") String value);

    @GetMapping("/deleteKey")
    AjaxResult deleteKey(
            @RequestParam("key") String key);
}
