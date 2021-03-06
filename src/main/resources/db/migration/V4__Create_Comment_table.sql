create table COMMENT
(
    id BIGINT auto_increment,
    parent_id BIGINT not null,
    type int not null,
    commentator int not null,
    gmt_create BIGINT not null,
    gmt_modified BIGINT not null,
    like_count BIGINT default 0 not null,
    constraint COMMENT_PK
        primary key (id)
);
