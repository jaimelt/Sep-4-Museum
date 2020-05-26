package com.example.android_sep4.repositories;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.android_sep4.model.Visitor;
import com.example.android_sep4.model.Visitors;
import com.example.android_sep4.requests.ServiceGenerator;
import com.example.android_sep4.requests.VisitorsEndpoints;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@RequiresApi(api = Build.VERSION_CODES.O)
public class VisitorsRepository {
    private static VisitorsRepository instance;
    private ArrayList<String> maleFirstNames = new ArrayList<>();
    private ArrayList<String> femaleFirstNames = new ArrayList<>();
    private ArrayList<String> lastNames = new ArrayList<>();
    private ArrayList<String> reasons = new ArrayList<>();
    private String[] countries = new String[]{"Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Anguilla", "Antarctica", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia and Herzegowina", "Botswana", "Bouvet Island", "Brazil", "British Indian Ocean Territory", "Brunei Darussalam", "Bulgaria", "Burkina Faso", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Cayman Islands", "Central African Republic", "Chad", "Chile", "China", "Christmas Island", "Cocos (Keeling) Islands", "Colombia", "Comoros", "Congo", "Congo, the Democratic Republic of the", "Cook Islands", "Costa Rica", "Cote d'Ivoire", "Croatia (Hrvatska)", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Falkland Islands (Malvinas)", "Faroe Islands", "Fiji", "Finland", "France", "France Metropolitan", "French Guiana", "French Polynesia", "French Southern Territories", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Gibraltar", "Greece", "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Heard and Mc Donald Islands", "Holy See (Vatican City State)", "Honduras", "Hong Kong", "Hungary", "Iceland", "India", "Indonesia", "Iran (Islamic Republic of)", "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Korea, Democratic People's Republic of", "Korea, Republic of", "Kuwait", "Kyrgyzstan", "Lao, People's Democratic Republic", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libyan Arab Jamahiriya", "Liechtenstein", "Lithuania", "Luxembourg", "Macau", "Macedonia, The Former Yugoslav Republic of", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Martinique", "Mauritania", "Mauritius", "Mayotte", "Mexico", "Micronesia, Federated States of", "Moldova, Republic of", "Monaco", "Mongolia", "Montserrat", "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "Netherlands Antilles", "New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island", "Northern Mariana Islands", "Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Pitcairn", "Poland", "Portugal", "Puerto Rico", "Qatar", "Reunion", "Romania", "Russian Federation", "Rwanda", "Saint Kitts and Nevis", "Saint Lucia", "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Seychelles", "Sierra Leone", "Singapore", "Slovakia (Slovak Republic)", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Georgia and the South Sandwich Islands", "Spain", "Sri Lanka", "St. Helena", "St. Pierre and Miquelon", "Sudan", "Suriname", "Svalbard and Jan Mayen Islands", "Swaziland", "Sweden", "Switzerland", "Syrian Arab Republic", "Taiwan, Province of China", "Tajikistan", "Tanzania, United Republic of", "Thailand", "Togo", "Tokelau", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Turks and Caicos Islands", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "United States Minor Outlying Islands", "Uruguay", "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam", "Virgin Islands (British)", "Virgin Islands (U.S.)", "Wallis and Futuna Islands", "Western Sahara", "Yemen", "Yugoslavia", "Zambia", "Zimbabwe"};
    private Random random = new Random();
    private LocalDate date = LocalDate.of(2018, 1, 1);

    public static VisitorsRepository getInstance() {
        if (instance == null) {
            instance = new VisitorsRepository();
        }
        return instance;
    }

    private void setMalesFirstNames() {
        maleFirstNames.add("John");
        maleFirstNames.add("Andrew");
        maleFirstNames.add("David");
        maleFirstNames.add("Carl");
        maleFirstNames.add("Alexandr");
        maleFirstNames.add("Joshua");
        maleFirstNames.add("Kim");
        maleFirstNames.add("Lee");
        maleFirstNames.add("Vasile");
        maleFirstNames.add("Angel");
        maleFirstNames.add("Florin");
        maleFirstNames.add("Jaime");
        maleFirstNames.add("Dave");
        maleFirstNames.add("Valera");
        maleFirstNames.add("Steffen");
        maleFirstNames.add("Paulius");
        maleFirstNames.add("Adam");
        maleFirstNames.add("Jonathan");
        maleFirstNames.add("George");
        maleFirstNames.add("Michal");
        maleFirstNames.add("Krystian");
        maleFirstNames.add("Luis");
        maleFirstNames.add("Harry");
        maleFirstNames.add("Ilie");
        maleFirstNames.add("Liam");
        maleFirstNames.add("Andrezi");
        maleFirstNames.add("Ronald");
        maleFirstNames.add("Jason");
        maleFirstNames.add("Viktor");
        maleFirstNames.add("Darius");
        maleFirstNames.add("Chris");
        maleFirstNames.add("Fabian");
        maleFirstNames.add("Dementie");
        maleFirstNames.add("Sabin");
    }

    private void setFemaleFirstNames() {
        femaleFirstNames.add("Victoria");
        femaleFirstNames.add("Ana");
        femaleFirstNames.add("Sabrina");
        femaleFirstNames.add("Alicia");
        femaleFirstNames.add("Cristina");
        femaleFirstNames.add("Maria");
        femaleFirstNames.add("Iulia");
        femaleFirstNames.add("Emanuella");
        femaleFirstNames.add("Mila");
        femaleFirstNames.add("Lina");
        femaleFirstNames.add("Mette");
        femaleFirstNames.add("Andrea");
        femaleFirstNames.add("Kate");
        femaleFirstNames.add("Raluca");
        femaleFirstNames.add("Karla");
        femaleFirstNames.add("Alexandra");
        femaleFirstNames.add("Anastasia");
        femaleFirstNames.add("Paula");
        femaleFirstNames.add("Diana");
        femaleFirstNames.add("Eva");
        femaleFirstNames.add("Magda");
        femaleFirstNames.add("Suzzana");
        femaleFirstNames.add("Mia");
        femaleFirstNames.add("Petra");
        femaleFirstNames.add("Carolina");
        femaleFirstNames.add("Alina");
        femaleFirstNames.add("Patricia");
    }

    private void setLastNames() {
        lastNames.add("Smith");
        lastNames.add("Johnson");
        lastNames.add("Williams");
        lastNames.add("Brown");
        lastNames.add("Bordei");
        lastNames.add("Lopez");
        lastNames.add("Marandici");
        lastNames.add("Ionel");
        lastNames.add("Milles");
        lastNames.add("Davis");
        lastNames.add("Jones");
        lastNames.add("Rusu");
        lastNames.add("Kuni");
        lastNames.add("Aspas");
        lastNames.add("King");
        lastNames.add("Taylor");
        lastNames.add("Sirbu");
        lastNames.add("Morgan");
        lastNames.add("Perry");
        lastNames.add("Rodriguez");
        lastNames.add("Harris");
        lastNames.add("Howard");
        lastNames.add("Bryant");
        lastNames.add("Lee");
        lastNames.add("Sanders");
        lastNames.add("Foster");
        lastNames.add("Stark");
        lastNames.add("Romanov");
        lastNames.add("Flores");
        lastNames.add("Russell");
        lastNames.add("Diaz");
        lastNames.add("Nelson");
        lastNames.add("Evans");
        lastNames.add("Ferdinand");
        lastNames.add("Fernandes");
        lastNames.add("Dos Santos");
    }

    private void setReasons() {
        reasons.add("Drawings");
        reasons.add("Paintings");
        reasons.add("Ceramics");
        reasons.add("Photos");
        reasons.add("Sculptures");
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public Visitor createRandomMaleVisitor(LocalDate date) {
        return new Visitor(getRandomMaleName(), getRandomLastName(), "Male", getRandomCountry(), getRandomAge(), getRandomReason(), date);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public Visitor createRandomFemaleVisitor(LocalDate date) {
        return new Visitor(getRandomFemaleName(), getRandomLastName(), "Female", getRandomCountry(), getRandomAge(), getRandomReason(), date);
    }


    private String getRandomMaleName() {
        return maleFirstNames.get(random.nextInt(maleFirstNames.size()));
    }

    private String getRandomFemaleName() {
        return femaleFirstNames.get(random.nextInt(femaleFirstNames.size()));
    }


    private String getRandomLastName() {
        return lastNames.get(random.nextInt(lastNames.size()));
    }

    private String getRandomCountry() {
        return countries[random.nextInt(countries.length)];
    }

    private int getRandomAge() {
        return random.nextInt(68) + 12;
    }

    private String getRandomReason() {
        return reasons.get(random.nextInt(reasons.size()));
    }

    private void addDay() {
        date = date.plusDays(1);
        System.out.println(date);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Visitors createVisitorsForDays() {
        setMalesFirstNames();
        setFemaleFirstNames();
        setLastNames();
        setReasons();

        ArrayList<Visitor> maleVisitors = new ArrayList<>();
        ArrayList<Visitor> femaleVisitors = new ArrayList<>();
        ArrayList<Visitor> visitors = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            addDay();
            for (int j = 0; j < random.nextInt(15) + 35; j++) {
                maleVisitors.add(j, createRandomMaleVisitor(date));
            }

            for (int j = 0; j < random.nextInt(15) + 35; j++) {
                femaleVisitors.add(j, createRandomFemaleVisitor(date));
            }
            visitors.addAll(maleVisitors);
            visitors.addAll(femaleVisitors);
        }
        return new Visitors(visitors);
    }

    public void sendVisitorsData() {
        VisitorsEndpoints endpoints = ServiceGenerator.getVisitorsEndpoints();

        Call<Visitors> call = endpoints.sendVisitors(createVisitorsForDays());
        call.enqueue(new Callback<Visitors>() {
            @Override
            public void onResponse(Call<Visitors> call, Response<Visitors> response) {
                System.out.println("Visitors sent");
            }

            @Override
            public void onFailure(Call<Visitors> call, Throwable t) {
                System.out.println("Visitors failed");
            }
        });

    }
}
