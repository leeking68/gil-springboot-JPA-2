package order.restaurant.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@DiscriminatorValue("A")
@Entity
@Getter
@Setter
public class Meat extends Item{
    private String animal;
    private String where;
}
