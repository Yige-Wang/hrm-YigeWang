package hrm.wyg.client;

import hrm.wyg.client.impl.SystemdictionaryItemClientImpl;
import hrm.wyg.domain.SystemdictionaryItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "system-service",fallback = SystemdictionaryItemClientImpl.class)
public interface SystemdictionaryItemClient {
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public SystemdictionaryItem get(@PathVariable("id")Long id);
}
