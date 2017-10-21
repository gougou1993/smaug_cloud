package smaug.cloud.provider.mappers.queue;

import org.apache.ibatis.annotations.Param;
import smaug.cloud.data.entity.queue.QueuingEntity;

public interface QueuingEntityMapper {

    QueuingEntity selectByPrimaryKey(@Param("serialId") Long serialid);

}