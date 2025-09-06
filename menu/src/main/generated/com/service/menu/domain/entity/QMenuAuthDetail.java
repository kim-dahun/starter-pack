package com.service.menu.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMenuAuthDetail is a Querydsl query type for MenuAuthDetail
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMenuAuthDetail extends EntityPathBase<MenuAuthDetail> {

    private static final long serialVersionUID = -736127038L;

    public static final QMenuAuthDetail menuAuthDetail = new QMenuAuthDetail("menuAuthDetail");

    public final StringPath comCd = createString("comCd");

    public final StringPath menuDetailId = createString("menuDetailId");

    public final StringPath menuId = createString("menuId");

    public final StringPath useFlag = createString("useFlag");

    public final StringPath userId = createString("userId");

    public QMenuAuthDetail(String variable) {
        super(MenuAuthDetail.class, forVariable(variable));
    }

    public QMenuAuthDetail(Path<? extends MenuAuthDetail> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMenuAuthDetail(PathMetadata metadata) {
        super(MenuAuthDetail.class, metadata);
    }

}

