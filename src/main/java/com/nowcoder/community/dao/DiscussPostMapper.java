package com.nowcoder.community.dao;

import com.nowcoder.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussPostMapper {

    //其实首页是所有帖子都显示的 但是为了以后的“我的帖子”，所以是动态的sql
    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit);

    // @Param注解用于给参数取别名,有的参数名会很复杂
    // 动态的sql：标签是<if>，拼一个条件 并且只有一个参数 不用别名会报错，这里是三个参数
    // 如果只有一个参数,并且在<if>里使用,则必须加别名.
    int selectDiscussPostRows(@Param("userId") int userId);

}
