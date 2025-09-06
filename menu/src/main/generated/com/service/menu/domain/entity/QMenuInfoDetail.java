package com.service.menu.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMenuInfoDetail is a Querydsl query type for MenuInfoDetail
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMenuInfoDetail extends EntityPathBase<MenuInfoDetail> {

    private static final long serialVersionUID = 747061832L;

    public static final QMenuInfoDetail menuInfoDetail = new QMenuInfoDetail("menuInfoDetail");

    public final StringPath menuDetailId = createString("menuDetailId");

    public final StringPath menuDetailName = createString("menuDetailName");

    public final StringPath remark = createString("remark");

    public final NumberPath<Integer> sortSeq = createNumber("sortSeq", Integer.class);

    public QMenuInfoDetail(String variable) {
        super(MenuInfoDetail.class, forVariable(variable));
    }

    public QMenuInfoDetail(Path<? extends MenuInfoDetail> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMenuInfoDetail(PathMetadata metadata) {
        super(MenuInfoDetail.class, metadata);
    }

}

