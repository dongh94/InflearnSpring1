package inflearn.spring01.member;

public interface MemberService {
    void join(Member member);

    Member findMember(Long memberId);

}
