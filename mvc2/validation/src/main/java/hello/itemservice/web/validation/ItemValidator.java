package hello.itemservice.web.validation;

import hello.itemservice.domain.item.Item;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ItemValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Item.class.isAssignableFrom(clazz);
        // 타입이 item == clazz 이냐. 파라미터로 넘어오는 clazz와 Item이 타입 일치하냐
        //item == itemChild . 자식클래스냐
    }

    @Override
    public void validate(Object target, Errors errors) { // Errors는 BindingResult의 부모 클래스 = Errors에는 BindingResult를 담을 수 있
        Item item = (Item) target; // 인터페이스 자체가 Object로 되어있어서 캐스팅 해줘야된다

        if (!StringUtils.hasText(item.getItemName())) {
            errors.rejectValue("itemName","required"); // errorCode의 .item.itemName은 알아서 찾아준다
        }
        if (item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000) {
            errors.rejectValue("price", "range",new Object[]{1000,1000000},null);
        }
        if (item.getQuantity() == null || item.getQuantity() > 10000) {
            errors.rejectValue("quantity","max",new Object[]{9999},null);
        }
        //특정 필드 예외가 아닌 전체 예외
        if (item.getPrice() != null && item.getQuantity() != null) {
            int resultPrice = item.getPrice() * item.getQuantity();
            if (resultPrice < 10000) {
                errors.reject("totalPriceMin",new Object[]{10000,resultPrice},null);
            }
        }
    }
}
