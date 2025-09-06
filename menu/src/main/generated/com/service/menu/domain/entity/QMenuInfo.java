package com.service.menu.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMenuInfo is a Querydsl query type for MenuInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMenuInfo extends EntityPathBase<MenuInfo> {

    private static final long serialVersionUID = -1177212137L;

    public static final QMenuInfo menuInfo = new QMenuInfo("menuInfo");

    public final DateTimePath<java.time.Instant> createDate = createDateTime("createDate", java.time.Instant.class);

    public final StringPath createUser = createString("createUser");

    public final StringPath menuId = createString("menuId");

    public final NumberPath<Integer> menuLevel = createNumber("menuLevel", Integer.class);

    public final StringPath menuName = createString("menuName");

    public final StringPath multiIngualCode = createString("multiIngualCode");

    public final StringPath parentMenuId = createString("parentMenuId");

    public final NumberPath<Integer> sortSeq = createNumber("sortSeq", Integer.class);

    public final DateTimePath<java.time.Instant> updateDate = createDateTime("updateDate", java.time.Instant.class);

    public final StringPath updateUser = createString("updateUser");

    public QMenuInfo(String variable) {
        super(MenuInfo.class, forVariable(variable));
    }

    public QMenuInfo(Path<? extends MenuInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMenuInfo(PathMetadata metadata) {
        super(MenuInfo.class, metadata);
    }

}

