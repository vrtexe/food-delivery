package mk.ukim.finki.uiktp.thefoodieexpress.data;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.uiktp.thefoodieexpress.dish.DishDto;
import mk.ukim.finki.uiktp.thefoodieexpress.dish.DishService;
import mk.ukim.finki.uiktp.thefoodieexpress.restaurant.Cuisine;
import mk.ukim.finki.uiktp.thefoodieexpress.restaurant.Restaurant;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
@RequiredArgsConstructor
public class DishDataInitializer {
    
    private final DishService dishService;
    private final RestaurantDataInitializer restaurantDataInitializer;
    
    private static final List<DishDto> data = List.of(
            new DishDto(null, "Tomato Duck", 233.71, "scelerisque neque. Nullam nisl. Maecenas malesuada fringilla est. Mauris eu turpis. Nulla aliquet. Proin velit. Sed malesuada", null, Cuisine.CHINESE),
            new DishDto(null, "Engine-Cooked Herbs", 327.11, "egestas lacinia. Sed congue, elit sed consequat auctor, nunc nulla vulputate dui, nec tempus mauris erat eget ipsum. Suspendisse sagittis. Nullam", null, Cuisine.CHINESE),
            new DishDto(null, "Stir-Fried Garlic Lobster", 258.76, "vestibulum lorem, sit amet ultricies sem magna nec quam. Curabitur vel lectus. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur", null, Cuisine.CHINESE),
            new DishDto(null, "Onion Herring", 236.81, "vestibulum massa rutrum magna. Cras convallis convallis dolor. Quisque tincidunt pede ac urna. Ut tincidunt vehicula risus. Nulla eget metus eu erat semper rutrum. Fusce", null, Cuisine.CHINESE),
            new DishDto(null, "Shallow-Fried Confit of Chutney", 140.83, "fames ac turpis egestas. Aliquam fringilla cursus purus. Nullam scelerisque neque sed sem egestas blandit. Nam nulla magna, malesuada vel, convallis", null, Cuisine.MEXICAN),
            new DishDto(null, "Mustard Tart", 203.57, "elit. Nulla facilisi. Sed neque. Sed eget lacus. Mauris non dui nec urna suscipit nonummy. Fusce fermentum fermentum arcu. Vestibulum ante ipsum primis in", null, Cuisine.MEXICAN),
            new DishDto(null, "Blueberry and Ginger Cake", 136.43, "pharetra, felis eget varius ultrices, mauris ipsum porta elit, a feugiat tellus lorem eu metus. In lorem. Donec elementum, lorem ut aliquam iaculis, lacus", null, Cuisine.MEXICAN),
            new DishDto(null, "Raspberry and Walnut Ice Lollies", 314.98, "arcu vel quam dignissim pharetra. Nam ac nulla. In tincidunt congue turpis. In condimentum. Donec at arcu. Vestibulum", null, Cuisine.MEXICAN),
            new DishDto(null, "Almond Milk", 259.61, "ac ipsum. Phasellus vitae mauris sit amet lorem semper auctor. Mauris vel turpis. Aliquam adipiscing", null, Cuisine.FRENCH),
            new DishDto(null, "Cashew Pound Cake", 161.76, "mus. Proin vel arcu eu odio tristique pharetra. Quisque ac libero nec ligula consectetuer rhoncus. Nullam velit dui, semper et, lacinia vitae, sodales at,", null, Cuisine.FRENCH),
            new DishDto(null, "Pan-Fried Confit of Yak", 149.34, "gravida. Aliquam tincidunt, nunc ac mattis ornare, lectus ante dictum mi, ac mattis velit justo nec ante. Maecenas mi felis, adipiscing fringilla, porttitor", null, Cuisine.FRENCH),
            new DishDto(null, "Stir-Fried Pepper", 412.96, "urna, nec luctus felis purus ac tellus. Suspendisse sed dolor. Fusce mi lorem, vehicula et,", null, Cuisine.FRENCH),
            new DishDto(null, "Gentle-Fried Figs", 220.82, "commodo hendrerit. Donec porttitor tellus non magna. Nam ligula elit, pretium et, rutrum non, hendrerit id, ante. Nunc mauris sapien, cursus in, hendrerit", null, Cuisine.INDIAN),
            new DishDto(null, "Broasted Basil", 208.89, "turpis non enim. Mauris quis turpis vitae purus gravida sagittis. Duis gravida. Praesent eu nulla at sem molestie sodales. Mauris blandit enim", null, Cuisine.INDIAN),
            new DishDto(null, "Basted Parmesan Pie", 473.39, "est, mollis non, cursus non, egestas a, dui. Cras pellentesque. Sed dictum. Proin eget odio. Aliquam vulputate ullamcorper magna.", null, Cuisine.INDIAN),
            new DishDto(null, "Pressure-Cooked Lemongrass Bisque", 112.80, "nunc sit amet metus. Aliquam erat volutpat. Nulla facilisis. Suspendisse commodo tincidunt nibh. Phasellus nulla. Integer vulputate, risus a ultricies", null, Cuisine.INDIAN),
            new DishDto(null, "Ginger and Cocoa Gingerbread", 462.28, "luctus et ultrices posuere cubilia Curae Donec tincidunt. Donec vitae erat vel pede blandit congue. In scelerisque scelerisque dui. Suspendisse ac metus vitae velit egestas", null, Cuisine.ITALIAN),
            new DishDto(null, "Apple and Plum Gingerbread", 420.21, "Vivamus nisi. Mauris nulla. Integer urna. Vivamus molestie dapibus ligula. Aliquam erat volutpat. Nulla dignissim. Maecenas ornare egestas ligula. Nullam feugiat placerat velit. Quisque varius.", null, Cuisine.ITALIAN),
            new DishDto(null, "Melon Fruitcake", 114.71, "interdum. Nunc sollicitudin commodo ipsum. Suspendisse non leo. Vivamus nibh dolor, nonummy ac, feugiat non, lobortis quis, pede.", null, Cuisine.ITALIAN),
            new DishDto(null, "Almond Strudel", 393.47, "malesuada id, erat. Etiam vestibulum massa rutrum magna. Cras convallis convallis dolor. Quisque tincidunt pede ac", null, Cuisine.JAPANESE),
            new DishDto(null, "Engine-Cooked Mushroom Yak", 356.70, "Cras dolor dolor, tempus non, lacinia at, iaculis quis, pede. Praesent eu dui. Cum sociis natoque penatibus et magnis dis", null, Cuisine.JAPANESE),
            new DishDto(null, "Engine-Cooked Sweet", 426.79, "Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Aliquam fringilla cursus purus. Nullam scelerisque neque sed sem egestas blandit. Nam", null, Cuisine.JAPANESE),
            new DishDto(null, "Stir-Fried Jasmine Shrimps", 429.22, "velit justo nec ante. Maecenas mi felis, adipiscing fringilla, porttitor vulputate, posuere vulputate, lacus. Cras interdum. Nunc", null, Cuisine.SPANISH),
            new DishDto(null, "Fried Sugar Herring", 409.62, "Vivamus nisi. Mauris nulla. Integer urna. Vivamus molestie dapibus ligula. Aliquam erat volutpat. Nulla dignissim. Maecenas ornare egestas ligula. Nullam feugiat placerat", null, Cuisine.SPANISH),
            new DishDto(null, "Brined Honey Gratin", 434.22, "egestas a, dui. Cras pellentesque. Sed dictum. Proin eget odio. Aliquam vulputate ullamcorper magna. Sed", null, Cuisine.SPANISH),
            new DishDto(null, "Cured Yogurt Spring Vegetables", 142.51, "faucibus lectus, a sollicitudin orci sem eget massa. Suspendisse eleifend. Cras sed leo. Cras vehicula aliquet libero. Integer in magna.", null, Cuisine.SPANISH),
            new DishDto(null, "Mango and Peach Ice Cream", 136.40, "Ut sagittis lobortis mauris. Suspendisse aliquet molestie tellus. Aenean egestas hendrerit neque. In ornare sagittis felis. Donec tempor,", null, Cuisine.THAI),
            new DishDto(null, "Milk Chocolate and Nutmeg Cone", 170.31, "nisi. Mauris nulla. Integer urna. Vivamus molestie dapibus ligula. Aliquam erat volutpat. Nulla dignissim. Maecenas ornare egestas ligula. Nullam feugiat placerat velit. Quisque varius. Nam", null, Cuisine.THAI),
            new DishDto(null, "Cardamom Pud", 257.69, "bibendum. Donec felis orci, adipiscing non, luctus sit amet, faucibus ut, nulla. Cras eu tellus eu augue porttitor interdum. Sed auctor odio a", null, Cuisine.THAI),
            new DishDto(null, "Praline Pie", 333.51, "nonummy ipsum non arcu. Vivamus sit amet risus. Donec egestas. Aliquam nec enim. Nunc ut erat. Sed nunc", null, Cuisine.TURKISH),
            new DishDto(null, "Smoked Orange Pigeon", 230.73, "vulputate, risus a ultricies adipiscing, enim mi tempor lorem, eget mollis lectus pede et risus.", null, Cuisine.TURKISH),
            new DishDto(null, "Stuffed Hot", 270.70, "augue malesuada malesuada. Integer id magna et ipsum cursus vestibulum. Mauris magna. Duis dignissim tempor arcu. Vestibulum ut eros", null, Cuisine.TURKISH),
            new DishDto(null, "Shallow-Fried Peppermint Cockles", 362.26, "erat volutpat. Nulla dignissim. Maecenas ornare egestas ligula. Nullam feugiat placerat velit. Quisque varius. Nam porttitor scelerisque neque. Nullam nisl.", null, Cuisine.MACEDONIAN),
            new DishDto(null, "Pressure-Cooked Dark Beer Clams", 108.63, "Nulla interdum. Curabitur dictum. Phasellus in felis. Nulla tempor augue ac ipsum. Phasellus vitae mauris sit amet lorem semper auctor. Mauris", null, Cuisine.MACEDONIAN),
            new DishDto(null, "Poached Cranberry Pizza", 439.87, "montes, nascetur ridiculus mus. Proin vel arcu eu odio tristique pharetra. Quisque ac libero nec ligula consectetuer rhoncus. Nullam velit dui,", null, Cuisine.MACEDONIAN),
            new DishDto(null, "Simmered Pepper", 215.63, "sem ut dolor dapibus gravida. Aliquam tincidunt, nunc ac mattis ornare, lectus ante dictum mi,", null, Cuisine.MACEDONIAN),
            new DishDto(null, "Chestnut and Pecan Pud", 267.12, "dui, in sodales elit erat vitae risus. Duis a mi fringilla mi lacinia mattis. Integer eu", null, Cuisine.MACEDONIAN),
            new DishDto(null, "Nutmeg and Banana Cheesecake", 359.64, "sit amet diam eu dolor egestas rhoncus. Proin nisl sem, consequat nec, mollis vitae, posuere at, velit. Cras lorem lorem, luctus ut,", null, Cuisine.MACEDONIAN),
            new DishDto(null, "Mint Snacks", 375.95, "bibendum. Donec felis orci, adipiscing non, luctus sit amet, faucibus ut, nulla. Cras eu tellus eu", null, Cuisine.MACEDONIAN),
            new DishDto(null, "Ginger Soufflé", 299.10, "accumsan sed, facilisis vitae, orci. Phasellus dapibus quam quis diam. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac", null, Cuisine.MACEDONIAN)
    );
    public static <E> List<E> pickNRandomElements(List<E> list, int n, Random r) {
        int length = list.size();

        if (length < n) return null;

        for (int i = length - 1; i >= length - n; --i)
        {
            Collections.swap(list, i , r.nextInt(i + 1));
        }
        return list.subList(length - n, length);
    }

    public static <E> List<E> pickNRandomElements(List<E> list, int n) {
        return pickNRandomElements(list, n, new Random());
    }
    @PostConstruct
    public void initializeDummyData() {
        var restaurants = restaurantDataInitializer.getData();

        var randomGenerator = new Random();
        var MIN = 10;
        var MAX = 15;
        var dishes = new ArrayList<>(data);
        for (Restaurant r:restaurants)  {
            var numberOfDishes = randomGenerator.nextInt(MAX- MIN) + MIN;
            var restaurantDishes = pickNRandomElements(dishes, numberOfDishes);
            System.out.println(restaurantDishes);
            restaurantDishes.stream().map(dishDto ->
                    new DishDto(
                            dishDto.getId(),
                            dishDto.getName(),
                            dishDto.getPrice(),
                            dishDto.getDescription(),
                            r.getId(),
                            dishDto.getCuisine()
                    )
            ).forEach(dishService::create);
        }
    }
}
