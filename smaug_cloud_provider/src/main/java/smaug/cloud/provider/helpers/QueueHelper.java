package smaug.cloud.provider.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import smaug.cloud.api.vos.queue.Queuing;
import smaug.cloud.data.entity.queue.QueuingEntity;
import smaug.cloud.provider.mappers.queue.QueuingEntityMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by naonao on 17/10/21.
 */
@Component("queueHelper")
public class QueueHelper {
    @Autowired
    private QueuingEntityMapper queuingEntityMapper;

    public List<Queuing> queue() {
        QueuingEntity queue = queuingEntityMapper.selectByPrimaryKey((long)160945102);
        List<Queuing> queuings = new ArrayList<>();
        Queuing queuing = new Queuing();
        queuing.setSerialId(queue.getSerialid().intValue());
        return queuings;
    }

}
