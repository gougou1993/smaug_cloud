package smaug.cloud.provider.mappers.queue;

import smaug.cloud.data.entity.queue.QueuingEntity;

public interface QueuingEntityMapper {
    int deleteByPrimaryKey(Long serialid);

    int insert(QueuingEntity record);

    int insertSelective(QueuingEntity record);

    QueuingEntity selectByPrimaryKey(Long serialid);

    int updateByPrimaryKeySelective(QueuingEntity record);

    int updateByPrimaryKey(QueuingEntity record);
}