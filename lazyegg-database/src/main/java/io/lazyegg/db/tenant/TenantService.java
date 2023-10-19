package io.lazyegg.db.tenant;

/**
 * TenantService
 * 租户接口服务
 *
 * @author DifferentW  nsmeng3@163.com 2023/1/5 11:45
 */
public interface TenantService {

    /**
     * 获取多租户id
     *
     * @return 租户id
     */
    long getTenantId();

    /**
     * 获取多租户字段名
     *
     * @return 租户字段名
     */
    String getTenantColumnName();
}
