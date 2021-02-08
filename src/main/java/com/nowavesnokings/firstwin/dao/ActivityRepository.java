package com.nowavesnokings.firstwin.dao;

import com.nowavesnokings.firstwin.pojo.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author ssx
 * @version V1.0
 * @className ActivityRepository
 * @description 活动Repository对象
 * @date 2020-12-24 10:07
 * @since 1.8
 */
@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    /**
     * 根据活动名获取已发布活动.
     *
     * @param name the name
     * @return the activity by name
     */
    Optional<Activity> getActivityByNameAndOnlineTrue(String name);

    /**
     * 根据活动id集合获取活动列表.
     *
     * @param activityIds the activity ids
     * @param nowStart    the now start
     * @param nowEnd      the now end
     * @return the activities by id in
     */
    List<Activity> getActivitiesByIdInAndOnlineTrueAndStartTimeLessThanAndEndTimeGreaterThanEqual(List<Long> activityIds, Date nowStart, Date nowEnd);

    /**
     * 根据活动id获取没有过期的活动.
     *
     * @param id     the id
     * @param online the online
     * @return the activity by id and start time less than and end time greater than equal and
     */
    Optional<Activity> getActivityByIdAndOnline(Long id, Boolean online);
}
