package mk.ukim.finki.uiktp.thefoodieexpress.data;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.uiktp.thefoodieexpress.restaurant.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
@RequiredArgsConstructor
public class RestaurantDataInitializer {
    
    private final RestaurantService restaurantService;
    private static final Collection<RestaurantDto> data =
            List.of(
                    new RestaurantDto(null, "Core Food", 4, "23:48-23-48", "363-7045 Amet Avenue", "072224484"),
                    new RestaurantDto(null, "System Feed", 2, "16:36-16-36", "580-3515 Dapibus Street", "074452217"),
                    new RestaurantDto(null, "Gusto Food", 3, "12:10-12-10", "Ap #519-3614 Curabitur Ave", "070790955"),
                    new RestaurantDto(null, "Foodhut", 2, "18:05-18-05", "Ap #443-8366 Leo, Rd.", "+389071784253"),
                    new RestaurantDto(null, "Upscale Food", 2, "14:07-14-07", "Ap #558-1484 Vestibulum, Ave", "+389078487761"),
                    new RestaurantDto(null, "Project Feed", 2, "15:35-15-35", "7240 Aliquam Rd.", "072956405"),
                    new RestaurantDto(null, "Supreme Food", 2, "20:26-20-26", "P.O. Box 434, 3558 Elementum, Avenue", "075316387"),
                    new RestaurantDto(null, "Feed Food", 2, "10:57-10-57", "368-3672 Rutrum Rd.", "071824701"),
                    new RestaurantDto(null, "Cater Food", 3, "10:31-10-31", "811-4374 Libero. St.", "+389073838416"),
                    new RestaurantDto(null, "Harmony Food", 1, "07:35-07-35", "280-9141 Integer Rd.", "076736230"),
                    new RestaurantDto(null, "Fortify Food", 5, "23:29-23-29", "Ap #798-1600 A, Rd.", "+389072375135"),
                    new RestaurantDto(null, "Elite Meal", 4, "02:35-02-35", "Ap #943-6191 Sit Road", "070470775"),
                    new RestaurantDto(null, "Aladdin Food", 4, "06:26-06-26", "Ap #475-5372 Mauris Rd.", "+389072209333"),
                    new RestaurantDto(null, "Stand Food", 3, "17:28-17-28", "Ap #408-4254 Adipiscing St.", "073993611"),
                    new RestaurantDto(null, "Presto Food", 2, "13:49-13-49", "P.O. Box 108, 3844 Turpis Avenue", "+389071482141"),
                    new RestaurantDto(null, "Micro Food", 4, "09:36-09-36", "Ap #572-7537 Posuere Rd.", "+389079137737"),
                    new RestaurantDto(null, "Roll Food", 3, "01:45-01-45", "797-2254 Lobortis St.", "078204375"),
                    new RestaurantDto(null, "Tough Feed", 2, "15:53-15-53", "990-6663 Amet, St.", "074371433"),
                    new RestaurantDto(null, "Foodorama", 5, "20:46-20-46", "P.O. Box 865, 1222 Odio St.", "073267238"),
                    new RestaurantDto(null, "Dash Meal", 3, "09:30-09-30", "7764 Sed Av.", "077966322"),
                    new RestaurantDto(null, "Dynasty Feed", 1, "08:37-08-37", "Ap #731-991 Libero Rd.", "079362595"),
                    new RestaurantDto(null, "Connoisseur Food", 1, "07:37-07-37", "4896 Amet, Avenue", "+389071194354"),
                    new RestaurantDto(null, "Bio Food", 2, "18:31-18-31", "609-4717 Mauris Road", "+389071494597"),
                    new RestaurantDto(null, "Piece Food", 5, "03:32-03-32", "Ap #421-6539 Nec Street", "+389078378183"),
                    new RestaurantDto(null, "Push Meal", 2, "19:30-19-30", "Ap #107-834 Ligula. Ave", "+389078252474"),
                    new RestaurantDto(null, "Enhance Food", 2, "01:42-01-42", "5220 Neque. St.", "075480582"),
                    new RestaurantDto(null, "Cut Food", 4, "05:24-05-24", "Ap #213-5731 Risus Street", "+389077416727"),
                    new RestaurantDto(null, "Sensation Food", 5, "15:54-15-54", "Ap #258-4025 Elit, St.", "+389079874486"),
                    new RestaurantDto(null, "Nourish Food", 5, "16:16-16-16", "Ap #759-3815 Aliquam Rd.", "079982888"),
                    new RestaurantDto(null, "Bench Meal", 3, "14:21-14-21", "Ap #238-112 Eget Rd.", "+389076339159"),
                    new RestaurantDto(null, "Cuisine Food", 5, "07:43-07-43", "475-550 At, St.", "076245272"),
                    new RestaurantDto(null, "Compass Feed", 5, "14:36-14-36", "8619 Nibh Street", "+389071568656"),
                    new RestaurantDto(null, "Edge Food", 4, "10:45-10-45", "7629 Aliquet Av.", "074617532"),
                    new RestaurantDto(null, "Foodtastic", 3, "17:35-17-35", "723-6134 Vitae, St.", "075385433"),
                    new RestaurantDto(null, "Foodiva", 2, "03:36-03-36", "388-1257 Morbi Rd.", "074468785"),
                    new RestaurantDto(null, "Foodzen", 2, "10:12-10-12", "662-1132 Lacus. Street", "074374841"),
                    new RestaurantDto(null, "Foodjet", 4, "05:10-05-10", "797-7142 Magna. Rd.", "+389072281204"),
                    new RestaurantDto(null, "Foodegy", 3, "08:21-08-21", "Ap #554-8750 Gravida St.", "+389075441429"),
                    new RestaurantDto(null, "Wisdom Food", 4, "03:52-03-52", "187-9472 Litora Avenue", "+389072385748"),
                    new RestaurantDto(null, "Devilish Feed", 4, "09:00-09-00", "4705 Nulla St.", "071504734")
            );
    
    
    public Collection<Restaurant> getData() {
        return restaurantService.getAll();
    }
    
        @PostConstruct
    public void initializeDummyData() {
        data.forEach(restaurantService::create);
    }
}
