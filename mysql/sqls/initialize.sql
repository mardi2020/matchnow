create database if not exists matchnow;
use matchnow;

create table if not exists users
(
    user_id       bigint auto_increment
        primary key,
    created_date  datetime(6)  null,
    modified_date datetime(6)  null,
    blog_link     varchar(255) null,
    email         varchar(255) not null,
    github_link   varchar(255) null,
    job           varchar(255) null,
    password      varchar(255) not null,
    role          varchar(255) not null,
    username      varchar(255) not null,
    constraint UK_6dotkott2kjsp8vw4d0m25fb7
        unique (email),
    constraint UK_r43af9ap4edm43mmtq01oddj6
        unique (username)
);

create table if not exists messages
(
    message_id       bigint auto_increment
        primary key,
    main_text        varchar(255)                        not null,
    send_date        timestamp default CURRENT_TIMESTAMP not null,
    title            varchar(255)                        not null,
    recevier_user_id bigint                              null,
    sender_user_id   bigint                              null,
    constraint FK33dryxa6uu3erf90txk0mjpqn
        foreign key (recevier_user_id) references users (user_id),
    constraint FKk4mpqp6gfuaelpcamqv01brkr
        foreign key (sender_user_id) references users (user_id)
);

create table if not exists projects
(
    project_id    bigint auto_increment
        primary key,
    created_date  datetime(6)   null,
    modified_date datetime(6)   null,
    category      varchar(255)  null,
    input_image   varchar(255)  null,
    main_text     varchar(255)  not null,
    now_cnt       int default 1 null,
    state         int           null,
    title         varchar(255)  not null,
    want_cnt      int           not null,
    user_id       bigint        not null,
    constraint FKhswfwa3ga88vxv1pmboss6jhm
        foreign key (user_id) references users (user_id)
);

create table if not exists comments
(
    comment_id         bigint auto_increment
        primary key,
    created_date       datetime(6)  null,
    modified_date      datetime(6)  null,
    comment_text       varchar(255) not null,
    project_project_id bigint       not null,
    comment_user_id    bigint       not null,
    constraint FKp4nvv6gm20q63fcxtevikg1c8
        foreign key (comment_user_id) references users (user_id),
    constraint FKqp8gf8axo3xen3vcq429ncer6
        foreign key (project_project_id) references projects (project_id)
            on delete cascade
);

create table if not exists skill_stacks
(
    skill_stack_id bigint auto_increment
        primary key,
    stack_name     varchar(255) not null,
    user_skill_id  bigint       not null,
    constraint UK_ker6wjtngb2st9h5ikxqrtgxc
        unique (stack_name),
    constraint FK42mtsl02e8kg2rspyywrq8ejt
        foreign key (user_skill_id) references users (user_id)
);
