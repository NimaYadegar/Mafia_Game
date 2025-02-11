package com.nima.mafia_game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class CaractersInformationActivity extends MenuOptions {

    ImageView car_image;
    TextView desc_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caracters_information);


        car_image = (ImageView) findViewById(R.id.caracter_image);
        desc_tv = (TextView) findViewById(R.id.description_tv);

        setDescription();

    }

    private void setDescription() {

        String status = MainActivity.status;
        switch (status) {

            case "help" :
                car_image.setImageResource(R.drawable.all1);
                desc_tv.setText("\t\t\t" + "بازی مافیا" + "\n\n\t\t" +  "مافیا (به انگلیسی: Mafia) که به عنوان گرگینه (به انگلیسی: werewolf)" +
                        " نیز شناخته می شود، یک بازی گروهی" +
                        " و استدلالی است که نبرد بین یک اقلیت آگاه" +
                        " و یک اکثریت ناآگاه را شبیه سازی می کند.\n" +
                        "\n" + "\t\t" +
                        "به طور کلی در این بازی قدرت تکلم، حفظ خونسردی و آوردن استدلال های منطقی" +
                        " نقش بسزایی در پیروزی دارد. بازیکنان به طور مخفیانه نقش شان مشخص می شود؛" +
                        " مافیاها همدیگر را می شناسند" +
                        " و شهروندان تنها از تعداد افراد مافیا آگاه هستند." + "\n\t\t" +
                        "در فاز شب بازی، گادفادر با مشورت با مافیا ها به صورت مخفیانه به یک شهروند" +
                        " شلیک می کنند. دکتر سعی می کند فردی که مافیا ها به او شلیک کرده اند،" +
                        " را نجات دهد. کارآگاه نیز در پی شناختن مافیا است و اگر مافیا را شناسایی" +
                        " کند باید با استدلال به بقیه شهروندان ثابت کند او مافیا است. گادفادر، مافیا، دکتر، شهروند" +
                        " و کارآگاه شخصیت های اصلی بازی هستند و ممکن است در بازی شخصیت های دیگری" +
                        " مانند اسنایپر، ساقی، روئینتن، کشیش، سایلنتر، بمبلات هم به بازی اضافه شوند. در طول فاز روز، تمام بازیکنان بازمانده" +
                        " در مورد هویت های مافیایی بحث می کنند و برای حذف یک مظنون رای گیری می کنند.\n" +
                        "\n" + "\t\t" +
                        "بازی ادامه می یابد تا زمانی که همهٔ مافیاها از بازی بیرون بروند (برد شهروندان)" +
                        " یا تعداد مافیاها و شهروندان برابر شود (برد مافیا) یا" +
                        " جوکر در رای گیری حذف شود و برنده بازی شود. در یک بازی معمولاً نقش ها باید" +
                        " به گونه ای چیده شود که برای هر شخصیت، شخصیت های مقابل و مکمل قرار گیرد." +
                        "\n\n\t\t" + "در روز ها گزینه ای وجود دارد که افراد می توانند استعلام افراد خارج از بازی را بدهند" +
                        " و ببینند که چه تعداد مافیا و چه تعداد شهروند بیرون هستند، (جوکر جزء هیچ کدام نیست)." +
                        "\n\t\t" + "تعداد استعلام ها در طول کل بازی به تعداد مافیا ها است." +
                        "\n\n\t\t" + "شب ها در این بازی به این صورت است که بعد از روز هر وقت که افراد آماده بودند،" +
                        " روی (شروع شب) کلیک می کنند و پس از پنج ثانیه بازیکن اول صدا زده می شود،" +
                        " با کلیک بر روی اسم خود، اگر نقش او در شب باید فعالیتی داشته باشد، اسامی افرادی که می تواند" +
                        " انتخاب کند، برایش نمایش داده می شود، پس از انتخاب فرد مورد نظرش و تایید کردن آن، " +
                        "پنج ثانیه فرصت قرار دادن گوشی سر جای خودش را دارد و پس از آن بازیکن دیگری صدا زده می شود." +
                        "\n\t\t" + "اما اگر نقش او در شب کاری انجام نمی دهد، 10 ثانیه باید صبر کند سپس روی (ادامه) کلیک کند و او نیز پنج ثانیه فرصت قرار دادن گوشی سر جای خودش را دارد." +
                        "\n\n\t\t" + "اگر بمبلات در روز با رای گیری بیرون رود باید گوشی به او داده شود و فقط خودش فرد را انتخاب کند." +
                        "\n\t\t" + " او نمی تواند همدست خود را انتخاب کند." +
                        "\n\n" + "**********************" + "\n\t\t" + "با سپاس از بازی کردن." + "\n\t\t" + "امیدوارم لذت ببرید." +
                        "\n\t\t" + "برنامه نویس : نیما یادگار" + "\n\t\t" + "نظرات، انتقادات و " +
                        "\n\t\t" + "پیشنهاداتتان را" + "\n\t\t" +
                        "به پیوی تلگرام با" + "\n\t\t" + "آیدی : NY007@" + "\n\t\t" + "ارسال کنید." +
                        "\n\t\t" + "**********************");
                break;
            case "citizen" :
                car_image.setImageResource(R.drawable.shahrvand);
                desc_tv.setText("شهروندان\n\n\t\tشهروندان در بازی " +
                        "از هویت هیچ کدام از بازیکنان دیگر اطلاع " +
                        "ندارند و در تلاش هستند تا تمام مافیاها را از" +
                        " شهر بیرون بیاندازند تا شهر دوباره امن شود.");
                break;
            case "شهروندان" :
                car_image.setImageResource(R.drawable.shahrvand);
                desc_tv.setText("برنده بازی : شهروندان\n\n\t\tشهروندان توانستند با موفقیت شهر را پس بگیرند." + ShufflingActivity.game_summery);
                break;
            case "mafia" :
                car_image.setImageResource(R.drawable.mafia);
                desc_tv.setText("مافیاها\n\n\t\tمافیاها از هویت یکدیگر در شب اول اطلاع پیدا میکنند" +
                        " و در تلاش هستند تا تعدادشان با شهروندان مساوی شود تا" +
                        " شهر در برابرشان به زانو در بیاید.");
                break;
            case "مافیاها" :
                car_image.setImageResource(R.drawable.mafia);
                desc_tv.setText("برنده بازی : گروه مافیا\n\n\t\tگروه مافیا توانستند شهر را مغلوب خود کند." + ShufflingActivity.game_summery);
                break;
            case "joker" :
                car_image.setImageResource(R.drawable.joker);
                desc_tv.setText("جوکر\n\n\t\tجوکر یک فرد خودسر و غیر قابل پیشبینی" +
                        " است و در تلاش است که در روز با رای گیری از شهر بیرون رود و همه چیز" +
                        " را نابود کند.");
                break;
            case "The Joker" :
                car_image.setImageResource(R.drawable.joker);
                desc_tv.setText("برنده بازی : جوکر\n\n\t\tجوکر توانستند شهر را غافل گیر کند." + ShufflingActivity.game_summery);
                break;
            case "مافیا" :
                car_image.setImageResource(R.drawable.mafia);
                if (MainActivity.des_status) {
                    desc_tv.setText("مافیای ساده\n\n\t\tدر شب به گادفادر در تصمیم شلیک مشورت می دهد " +
                            "و حضورش در مساوی شدن تعدادشان با شهروندان مهم است.");
                }else {
                    desc_tv.setText(ShufflingActivity.roll_message);
                }
                break;
            case "گادفادر" :
                car_image.setImageResource(R.drawable.godfather);
                if (MainActivity.des_status) {
                    desc_tv.setText("گادفادر\n\n\t\tگادفادر رئیس مافیا ها است و هر شب به یک نفر شلیک می کند و شب ها بیرون نمی رود.");
                }else {
                    desc_tv.setText(ShufflingActivity.roll_message);
                }
                break;
            case "marde_khavi" :
                car_image.setImageResource(R.drawable.mardekhavi);
                desc_tv.setText("مرد قوی (به زودی)\n\nشخصیت جدیدی که عضو دسته مافیا می باشد.");
                break;
            case "بمبلات" :
                car_image.setImageResource(R.drawable.bombalot);
                if (MainActivity.des_status) {
                    desc_tv.setText("بمبلات\n\n\t\tاگر بمبلات در روز بیرون رود یک نفر را همراه خودش بیرون می برد.");
                }else {
                    desc_tv.setText(ShufflingActivity.roll_message);
                }
                break;
            case "سایلنتر" :
                car_image.setImageResource(R.drawable.sylenser);
                if (MainActivity.des_status) {
                    desc_tv.setText("سایلنتر\n\n\t\tاو هر شب یک نفر را ساکت می کند، فرد ساکت شده روز بعدش نمی تواند حتی یک کلمه حرف بزند.\n\n\t\tسایلنتر نمی تواند دو شب متوالی یک نفر را ساکت کند.");
                }else {
                    desc_tv.setText(ShufflingActivity.roll_message);
                }
                break;
            case "شهروند" :
                car_image.setImageResource(R.drawable.shahrvand);
                if (MainActivity.des_status) {
                    desc_tv.setText("شهروند\n\n\t\tشهروند باید به شهر کمک کند و مافیا ها را تشخیص دهد.");
                }else {
                    desc_tv.setText(ShufflingActivity.roll_message);
                }
                break;
            case "دکتر" :
                car_image.setImageResource(R.drawable.doctor);
                if (MainActivity.des_status) {
                    desc_tv.setText("دکتر\n\n\t\tدکتر هر شب یک نفر را نجات می دهد و او را از شلیک گلوله در امان نگه می دارد\n\n\t\tدکتر در طول بازی فقط یک بار می تواند خودش را نجات دهد.");
                }else {
                    desc_tv.setText(ShufflingActivity.roll_message);
                }
                break;
            case "کاراگاه" :
                car_image.setImageResource(R.drawable.karagah2);
                if (MainActivity.des_status) {
                    desc_tv.setText("کاراگاه\n\n\t\tکاراگاه هر شب استعلام یک نفر را می گیرد تا ببیند که آن فرد مافیا است.\n\n\t\tدر شب استعلام، او پس از تایید فرد انتخاب شده یک کد سری دریافت می کند، اگر فردای آن شب همان کد را بشنود یعنی استعلام صحیح است، اما اگر کد دیگری را بشنود استعلام غلط است.\n\t\tاستعلام گاردفادر همواره غلط است.");
                }else {
                    desc_tv.setText(ShufflingActivity.roll_message);
                }
                break;
            case "اسنایپر" :
                car_image.setImageResource(R.drawable.taktirandaz);
                if (MainActivity.des_status) {
                    desc_tv.setText("اسنایپر\n\n\t\tاسنایپر در طول کل بازی به تعداد مافیا ها تیر دارد و در شب با انتخاب خودش به یک نفر شلیک می کند، اگر تیرش به گادفادر بخورد، اتفاقی نمیافتد، اما اگر به اشتباه به یک شهروند شلیک کند، خودش از بازی بیرون می افتد و دکتر هم نمی تواند او را نجات دهد.");
                }else {
                    desc_tv.setText(ShufflingActivity.roll_message);
                }
                break;
            case "روئینتن" :
                car_image.setImageResource(R.drawable.rointan);
                if (MainActivity.des_status) {
                    desc_tv.setText("روئینتن\n\n\t\tروئینتن در طول کل بازی در شب یک بار اگر تیر بخورد، نمی میرد.\n\t\tاما پس از یک بار تیر خوردن دیگر در برابر تیر خوردن مقاوم نیست.");
                }else {
                    desc_tv.setText(ShufflingActivity.roll_message);
                }
                break;
            case "ساقی" :
                car_image.setImageResource(R.drawable.saghi2);
                if (MainActivity.des_status) {
                    desc_tv.setText("ساقی\n\n\t\tساقی با مست کردن فرد مورد نظر بر روی نقشش در آن شب تاثیر می گذارد.\n\n\t\tاگر گادفادر را مست کند، تیر گادفادر در آن شب خطا می رود.\n\n\t\t" +
                            "اگر سایلنتر را مست کند، خود سایلنتر ساکت می شود.\n\n\t\tاگر دکتر را مست کند، تجاتش بی فایده است.\n\n\t\t" +
                            "اگر کاراگاه را مست کند، استعلامش برعکس خواهد بود، به جز گادفادر که همواره استعلام غلط است.\n\n\t\t" +
                            "اگر اسنایپر را مست کند، تیرش خطا می رود و در نتیجه اگر اسنایپر به اشتباه به یک شهروند شلیک کند، خودش بیرون نمی رود و به وسیله ساقی نجات پیدا می کند.\n\n\t\t" +
                            "اگر روئینتن را مست کند، آن شب مقاومتش در برابر تیر از بین می رود.\n\n\t\t" +
                            "اگر کشیش را مست کند، رهایی از ساکت بودنش تاثیری ندارد.");
                }else {
                    desc_tv.setText(ShufflingActivity.roll_message);
                }
                break;
            case "کشیش" :
                car_image.setImageResource(R.drawable.keshish);
                if (MainActivity.des_status) {
                    desc_tv.setText("کشیش\n\n\t\tکشیش می تواند با انتخاب درستش فرد ساکت شده توسط سایلنتر را از ساکت بودن رها کند.\n\n\t\tاو در طول کل بازی یک بار می تواند خودش را برای رهایی از ساکت بودن انتخاب کند.");
                }else {
                    desc_tv.setText(ShufflingActivity.roll_message);
                }
                break;
            case "جوکر" :
                car_image.setImageResource(R.drawable.joker);
                if (MainActivity.des_status) {
                    desc_tv.setText("جوکر\n\n\t\tجوکر اگر فقط به وسیله رای گیری در روز از بازی بیرون رود، بازی را برده است.");
                }else {
                    desc_tv.setText(ShufflingActivity.roll_message);
                }
                break;
            default:
                break;

        }

    }


    public void gotIt(View view) {

        finish();

    }
}