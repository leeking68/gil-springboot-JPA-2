package order.restaurant.domain;

import lombok.Getter;
import lombok.Setter;
import order.restaurant.domain.item.Item;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class MenuList {
    @Id
    @GeneratedValue
    @Column(name = "menulist_id")
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name="menulist_item",
            joinColumns = @JoinColumn(name = "menulist_id"),
            inverseJoinColumns = @JoinColumn(name="item_id"))
    private List<Item> items = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="parent_id")
    private MenuList parent;

    @OneToMany(mappedBy = "parent")
    private List<MenuList> child = new ArrayList<>();

    //==연관관계 메서드==//
    public void addChildCategory(MenuList child) {
        this.child.add(child);
        child.setParent(this);
    }
}
