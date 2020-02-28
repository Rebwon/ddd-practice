package ko.maeng.dddpractice.domain.member;

import ko.maeng.dddpractice.web.dto.PostsCount;

import java.util.List;

public interface MemberRepositoryCustom {
    List<Member> findByName(String name);
    List<PostsCount> findAllPostsCount();
    List<Member> findAllByPostsId(long postsId);
    List<Member> findDynamicQuery(String name, Email email, String password);
    List<Member> findDynamicQueryAdvance(String name, Email email, String password);
}
