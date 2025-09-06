package com.service.menu.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMenuGroupAuthDetail is a Querydsl query type for MenuGroupAuthDetail
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMenuGroupAuthDetail extends EntityPathBase<MenuGroupAuthDetail> {

    private static final long serialVersionUID = 581390831L;

    public static final QMenuGroupAuthDetail menuGroupAuthDetail = new QMenuGroupAuthDetail("menuGroupAuthDetail");

    public final StringPath comCd = createString("comCd");

    public final StringPath groupId = createString("groupId");

    public final StringPath menuDetailId = createString("menuDetailId");

    public final StringPath menuId = createString("menuId");

    public final StringPath useFlag = createString("useFlag");

    public QMenuGroupAuthDetail(String variable) {
        super(MenuGroupAuthDetail.class, forVariable(variable));
    }

    public QMenuGroupAuthDetail(Path<? extends MenuGroupAuthDetail> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMenuGroupAuthDetail(PathMetadata metadata) {
        super(MenuGroupAuthDetail.class, metadata);
    }

}

