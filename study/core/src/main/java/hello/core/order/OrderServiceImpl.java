package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private MemberRepository memberRepository = new MemoryMemberRepository();
    private DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId); // 해당 id를 갖는 회원의 등급 정보 필요하므로 조회
        int discountPrice = discountPolicy.discount(member, itemPrice);
        // 할인에 관련된 계산은 discountPolicy에서 알아서 해줌 -> DIP 원칙 잘 지켜짐

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
