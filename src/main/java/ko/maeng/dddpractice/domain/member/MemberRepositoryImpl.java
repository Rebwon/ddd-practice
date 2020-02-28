package ko.maeng.dddpractice.domain.member;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import ko.maeng.dddpractice.domain.article.Posts;
import ko.maeng.dddpractice.web.dto.PostsCategory;
import ko.maeng.dddpractice.web.dto.PostsCount;
import lombok.RequiredArgsConstructor;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.querydsl.core.types.ExpressionUtils.count;
import static ko.maeng.dddpractice.domain.article.QCategory.*;
import static ko.maeng.dddpractice.domain.article.QPosts.*;
import static ko.maeng.dddpractice.domain.member.QMember.*;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Member> findByName(String name) {
        return jpaQueryFactory.selectFrom(member)
                .where(member.name.eq(name))
                .fetch();
    }

    // SELECT {First}, {Second} FROM id = id;
    // {First SELECT} SELECT member.name FROM MEMBER
    // {Second SELECT} SELECT count(posts) FROM posts WHERE member_id = member.id
    // projection is return Type.
    // as = alias = return type field property name.
    @Override
    public List<PostsCount> findAllPostsCount() {
        return jpaQueryFactory
                .select(Projections.fields(PostsCount.class,
                        member.name.as("memberName"),
                        ExpressionUtils.as(
                                JPAExpressions.select(count(posts.id))
                                    .from(posts)
                                    .where(posts.member.eq(member)),
                                "postsCount")
                        ))
                .from(member)
                .fetch();
    }

    @Override
    public List<Member> findAllByPostsId(long postsId) {
        return jpaQueryFactory
                .selectFrom(member)
                .where(member.id.in(
                        JPAExpressions.select(posts.member.id)
                                .from(posts)
                                .where(posts.id.eq(postsId))))
                .fetch();
    }

    @Override
    public List<Member> findDynamicQuery(String name, Email email, String password) {
        BooleanBuilder builder = new BooleanBuilder();

        if(!StringUtils.isEmpty(name)) {
            builder.and(member.name.eq(name));
        }

        if(!ObjectUtils.isEmpty(email)) {
            builder.and(member.email.value.eq(email.getValue()));
        }

        if(!StringUtils.isEmpty(password)){
            builder.and(member.password.eq(password));
        }

        return jpaQueryFactory
                .selectFrom(member)
                .where(builder)
                .fetch();
    }

    @Override
    public List<Member> findDynamicQueryAdvance(String name, Email email, String password) {
        return jpaQueryFactory
                .selectFrom(member)
                .where(eqName(name),
                        eqEmail(email),
                        eqPassword(password))
                .fetch();
    }

    private BooleanExpression eqName(String name) {
        if(StringUtils.isEmpty(name)){
            return null;
        }
        return member.name.eq(name);
    }

    private BooleanExpression eqEmail(Email email) {
        if(ObjectUtils.isEmpty(email)){
            return null;
        }
        return member.email.value.eq(email.getValue());
    }

    private BooleanExpression eqPassword(String password) {
        if(StringUtils.isEmpty(password)){
            return null;
        }
        return member.password.eq(password);
    }
}
