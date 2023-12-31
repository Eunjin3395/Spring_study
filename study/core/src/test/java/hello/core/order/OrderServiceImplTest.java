package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

    @Test
    void createOrder(){
        MemberRepository memberRepository= new MemoryMemberRepository();
        DiscountPolicy discountPolicy = new RateDiscountPolicy();
        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository,discountPolicy);
        //orderService.createOrder(1L, "itemA", 10000);
    }

}