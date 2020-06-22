package hrm.wyg.controller;

import hrm.wyg.util.AjaxResult;
import hrm.wyg.util.RedisUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

@RestController
public class CatchController {

    @GetMapping("setStr")
    public AjaxResult set(@RequestParam("key") String key,@RequestParam("value") String value){
        try {
            RedisUtils.INSTANCE.set(key,value);
            return AjaxResult.me().setSuccess(true).setMessage("保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("保存失败");
        }
    }
    @GetMapping("/getStr")
    AjaxResult get(@RequestParam("key")String key){
        try {
            RedisUtils.INSTANCE.get(key);
            return AjaxResult.me().setSuccess(true).setMessage("获取成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("获取失败");
        }
    }

    @GetMapping("/setex")
    AjaxResult setex(
            @RequestParam("key") String key,
            @RequestParam("seconds")Integer seconds,
            @RequestParam("value") String value){
        Jedis jedis = null;
        try {
            jedis = RedisUtils.INSTANCE.getSource();
            jedis.setex(key,seconds,value);
            return AjaxResult.me().setSuccess(true).setMessage("设置成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("设置失败").setSuccess(false);
        }finally {
            if (jedis!=null){
                jedis.close();
            }
        }

    }


    @GetMapping("/setnx")
    AjaxResult setnx(
            @RequestParam("key") String key,
            @RequestParam("value") String value){
        Jedis jedis = null;
        try {
            jedis = RedisUtils.INSTANCE.getSource();
            Long result = jedis.setnx(key, value);
            return AjaxResult.me().setSuccess(true).setMessage("设置成功").setResultObj(result);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("设置失败");
        }finally {
            if (jedis!=null){
                jedis.close();
            }
        }
    }

    @GetMapping("/deleteKey")
    AjaxResult deleteKey(
            @RequestParam("key") String key){
        Jedis jedis = null;
        try {
            jedis = RedisUtils.INSTANCE.getSource();
            jedis.del(key);
            return AjaxResult.me().setMessage("删除成功").setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("删除失败").setSuccess(false);
        }finally {
            if (jedis!=null){
                jedis.close();
            }
        }
    }
}
