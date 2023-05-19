CREATE TABLE floating_population
(
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     year INT,
                                     quarter INT,
                                     commercial_code INT,
                                     total_flpop INT,
                                     male_flpop INT,
                                     female_flpop INT,
                                     age_10_flpop INT,
                                     age_20_flpop INT,
                                     age_30_flpop INT,
                                     age_40_flpop INT,
                                     age_50_flpop INT,
                                     age_60_flpop INT,
                                     time_1_flpop INT,
                                     time_2_flpop INT,
                                     time_3_flpop INT,
                                     time_4_flpop INT,
                                     time_5_flpop INT,
                                     time_6_flpop INT,
                                     mon_flpop INT,
                                     tue_flpop INT,
                                     wed_flpop INT,
                                     thu_flpop INT,
                                     fri_flpop INT,
                                     sat_flpop INT,
                                     sun_flpop INT
);

CREATE TABLE local
(
                       local_id BIGINT,
                       borough VARCHAR(255),
                       dong VARCHAR(255),
                       commercial_code INT UNIQUE,
                       area INT,
                       PRIMARY KEY(local_id)
);

create table post
(
                      post_id bigint not null,
                      created_date datetime(6),
                      last_modified_date datetime(6),
                      content tinytext not null,
                      file_path varchar(255),
                      title varchar(50) not null,
                      view integer default 0,
                      writer_id bigint,
                      primary key (post_id)
);

create table comment
(
                         comment_id bigint not null,
                         created_date datetime(6),
                         last_modified_date datetime(6),
                         content tinytext not null,
                         is_removed bit not null,
                         parent_id bigint,
                         posts_id bigint,
                         user_id bigint,
                         primary key (comment_id)
);

CREATE TABLE avg_operation_period
(
                                      id BIGINT NOT NULL AUTO_INCREMENT,
                                      area_name VARCHAR(50),
                                      gu_name VARCHAR(50),
                                      service_name VARCHAR(50),
                                      avg_period FLOAT,
                                      year INT,
                                      quarter INT,
                                      PRIMARY KEY (id)
);
CREATE TABLE rental_fee
(
                            id BIGINT NOT NULL AUTO_INCREMENT,
                            area_name VARCHAR(50),
                            gu_name VARCHAR(50),
                            rentalfee_total INT,
                            rentalfee_1st_floor INT,
                            rentalfee_except_1st_floor INT,
                            year INT,
                            quarter INT,
                            PRIMARY KEY (id)
);
CREATE TABLE floating_rental_fee_rank
(
                                          ranking int,
                                          area_name VARCHAR(255),
                                          value float,
                                          floating int,
                                          rental_fee int,
                                          PRIMARY KEY(ranking)
);
CREATE TABLE commerce_change
(
                                 id bigint AUTO_INCREMENT PRIMARY KEY,
                                 commerce_metrics varchar(255),
                                 dong varchar(255),
                                 quarter int,
                                 year int
);
CREATE TABLE facility (
                          facility_id bigint AUTO_INCREMENT PRIMARY KEY,
                          commercial_code int,
                          num_of_accommodation int,
                          num_of_airport int,
                          num_of_bank int,
                          num_of_bus_stop int,
                          num_of_bus_terminal int,
                          num_of_department_store int,
                          num_of_elementary_school int,
                          num_of_facility int,
                          num_of_general_hospital int,
                          num_of_government_office int,
                          num_of_high_school int,
                          num_of_hospital int,
                          num_of_kindergarten int,
                          num_of_middle_school int,
                          num_of_pharmacy int,
                          num_of_rail_station int,
                          num_of_subway int,
                          num_of_supermarket int,
                          num_of_theater int,
                          num_of_university int,
                          quarter int,
                          year int
);
CREATE TABLE income_consumption (
                                    id bigint AUTO_INCREMENT PRIMARY KEY,
                                    average_monthly_income bigint,
                                    commercial_code int,
                                    quarter int,
                                    total_amount_spent bigint,
                                    year int
);
CREATE TABLE industry_analysis (
                                   industry_id bigint AUTO_INCREMENT PRIMARY KEY,
                                   service_name varchar(255),
                                   commercial_code bigint,
                                   num_of_close_stores int,
                                   num_of_franchise_stores int,
                                   num_of_open_stores int,
                                   num_of_stores int,
                                   quarter int,
                                   total_num_of_stores int,
                                   year int
);
CREATE TABLE user (
                      user_id bigint AUTO_INCREMENT PRIMARY KEY,
                      create_time datetime(6),
                      kakao_email varchar(255),
                      kakao_id bigint,
                      kakao_nickname varchar(255),
                      kakao_profile_img varchar(255),
                      username varchar(255)
);
CREATE TABLE workplace_population (
                                      workplace_id bigint AUTO_INCREMENT PRIMARY KEY,
                                      commercial_code bigint,
                                      num_of10age_workplace int,
                                      num_of10men_workplace int,
                                      num_of10women_workplace int,
                                      num_of20age_workplace int,
                                      num_of20men_workplace int,
                                      num_of20women_workplace int,
                                      num_of30age_workplace int,
                                      num_of30men_workplace int,
                                      num_of30women_workplace int,
                                      num_of40age_workplace int,
                                      num_of40men_workplace int,
                                      num_of40women_workplace int,
                                      num_of50age_workplace int,
                                      num_of50men_workplace int,
                                      num_of50women_workplace int,
                                      num_of60age_workplace int,
                                      num_of60men_workplace int,
                                      num_of60women_workplace int,
                                      num_of_men_workplace int,
                                      num_of_women_workplace int,
                                      quarter int,
                                      total_num_of_workplace int,
                                      year int
);
CREATE TABLE sales_growth_rate (
                                   id int AUTO_INCREMENT PRIMARY KEY,
                                   dong varchar(255),
                                   growth_rate_figures double,
                                   quarter_four_total bigint,
                                   quarter_three_total bigint,
                                   ranking int,
                                   service_name varchar(255)
);
CREATE TABLE sales_analysis (
                                sales_id bigint AUTO_INCREMENT PRIMARY KEY,
                                age10sales bigint,
                                age20sales bigint,
                                age30sales bigint,
                                age40sales bigint,
                                age50sales bigint,
                                age60sales bigint,
                                commercial_code bigint,
                                female_sales bigint,
                                fri_sales bigint,
                                male_sales bigint,
                                mon_sales bigint,
                                num_of_stores bigint,
                                quarter int,
                                sales_per_quarter bigint,
                                sat_sales bigint,
                                service_name varchar(255),
                                sun_sales bigint,
                                thu_sales bigint,
                                time1sales bigint,
                                time2sales bigint,
                                time3sales bigint,
                                time4sales bigint,
                                time5sales bigint,
                                time6sales bigint,
                                tue_sales bigint,
                                wed_sales bigint,
                                weekend_sales bigint,
                                weekly_sales bigint,
                                year int,
                                commercial_name bigint,
                                dong varchar(255)
);
CREATE TABLE resident_population (
                                     resident_id bigint AUTO_INCREMENT PRIMARY KEY,
                                     commercial_code int,
                                     num_of_age10residents int,
                                     num_of_age20residents int,
                                     num_of_age30residents int,
                                     num_of_age40residents int,
                                     num_of_age50residents int,
                                     num_of_age60residents int,
                                     num_of_men10residents int,
                                     num_of_men20residents int,
                                     num_of_men30residents int,
                                     num_of_men40residents int,
                                     num_of_men50residents int,
                                     num_of_men60residents int,
                                     num_of_men_residents int,
                                     num_of_women10residents int,
                                     num_of_women20residents int,
                                     num_of_women30residents int,
                                     num_of_women40residents int,
                                     num_of_women50residents int,
                                     num_of_women60residents int,
                                     num_of_women_residents int,
                                     quarter int,
                                     total_num_of_residents int,
                                     year int
);
