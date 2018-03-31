<!DOCTYPE html>
<html>
<head>
    <title>文件上传</title>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <#include "common_css.ftl">
    <#include "common_js.ftl">
    <#--<link rel="stylesheet" href="/static/dist/css/bootstrap.min.css">-->
    <#--<link rel="stylesheet" href="/static/dist/css/font-awesome.min.css">-->
    <#--<link rel="stylesheet" href="/static/plugins/jqgrid/ui.jqgrid-bootstrap.css">-->
    <#--<link rel="stylesheet" href="/static/plugins/ztree/css/metroStyle/metroStyle.css">-->
    <#--<link rel="stylesheet" href="/static/css/main.css">-->
    <#--<script src="/static/dist/js/jquery.min.js"></script>-->
    <#--<script src="/static/plugins/layer/layer.js"></script>-->
    <#--<script src="/static/dist/js/bootstrap.min.js"></script>-->
    <#--<script src="/static/plugins/jqgrid/grid.locale-cn.js"></script>-->
    <#--<script src="/static/plugins/jqgrid/jquery.jqGrid.min.js"></script>-->
    <#--<script src="/static/plugins/ztree/jquery.ztree.all.min.js"></script>-->
    <#--<script src="/static/js/common.js"></script>-->
    <script src="/static/dist/js/ajaxupload.js"></script>
</head>
<body>
    <div id="ossDiv">
        <div class="grid-btn">
            <a class="btn btn-info" onclick="addConfig()"><i class="fa fa-sun-o"></i>&nbsp;云存储配置</a>
            <a class="btn btn-primary" id="upload"><i class="fa fa-plus"></i>&nbsp;上传文件</a>
            <a class="btn btn-danger" onclick="del()"><i class="fa fa-trash-o"></i>&nbsp;删除</a>
        </div>
        <table id="jqGrid"></table>
        <div id="jqGridPager"></div>
    </div>

    <div id="editOssDiv" class="panel panel-default hide">
        <div id="editHeaderDiv" class="panel-heading"></div>
        <form class="form-horizontal">
            <input type="text" class="form-control hide" id="id" name="id"/>
            <div class="form-group">
                <div class="col-sm-2 control-label">存储类型</div>
                <label class="radio-inline">
                    <input type="radio" name="type" value="1"/> 七牛
                </label>
                <label class="radio-inline">
                    <input type="radio" name="type" value="2"/> 阿里云
                </label>
                <label class="radio-inline">
                    <input type="radio" name="type" value="3"/> 腾讯云
                </label>
            </div>
            <div id="type_1" class="hide">
                <div class="form-group">
                    <div class="col-sm-2 control-label">&nbsp;</div>
                    <p class="form-control-static"><a href="https://portal.qiniu.com/signup?code=3l7qkr0rv847m" target="_blank">免费申请(七牛)10GB储存空间</a></p>
                </div>
                <div class="form-group">
                    <div class="col-sm-2 control-label">域名</div>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="qiniuDomain" name="qiniuDomain" placeholder="七牛绑定的域名"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-2 control-label">路径前缀</div>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="qiniuPrefix" name="qiniuPrefix" placeholder="不设置默认为空"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-2 control-label">AccessKey</div>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="qiniuAccessKey" name="qiniuAccessKey" placeholder="七牛AccessKey"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-2 control-label">SecretKey</div>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="qiniuSecretKey" name="qiniuSecretKey" placeholder="七牛SecretKey"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-2 control-label">空间名</div>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="qiniuBucketName" name="qiniuBucketName" placeholder="七牛存储空间名"/>
                    </div>
                </div>
            </div>
            <div id="type_2" class="hide">
                <div class="form-group">
                    <div class="col-sm-2 control-label">域名</div>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="aliyunDomain" name="aliyunDomain" placeholder="阿里云绑定的域名"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-2 control-label">路径前缀</div>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="aliyunPrefix" name="aliyunPrefix" placeholder="不设置默认为空"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-2 control-label">EndPoint</div>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="aliyunEndPoint" name="aliyunEndPoint" placeholder="阿里云EndPoint"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-2 control-label">AccessKeyId</div>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="aliyunAccessKeyId" name="aliyunAccessKeyId" placeholder="阿里云AccessKeyId"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-2 control-label">AccessKeySecret</div>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="aliyunAccessKeySecret" name="aliyunAccessKeySecret" placeholder="阿里云AccessKeySecret"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-2 control-label">BucketName</div>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="aliyunBucketName" name="aliyunBucketName" placeholder="阿里云BucketName"/>
                    </div>
                </div>
            </div>
            <div id="type_3" class="hide">
                <div class="form-group">
                    <div class="col-sm-2 control-label">域名</div>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="qcloudDomain" name="qcloudDomain" placeholder="腾讯云绑定的域名"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-2 control-label">路径前缀</div>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="qcloudPrefix" name="qcloudPrefix" placeholder="不设置默认为空"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-2 control-label">AppId</div>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="qcloudAppId" name="qcloudAppId" placeholder="腾讯云AppId"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-2 control-label">SecretId</div>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="qcloudSecretId" name="qcloudSecretId" placeholder="腾讯云SecretId"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-2 control-label">SecretKey</div>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="qcloudSecretKey" name="qcloudSecretKey" placeholder="腾讯云SecretKey"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-2 control-label">BucketName</div>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="qcloudBucketName" name="qcloudBucketName" placeholder="腾讯云BucketName"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-2 control-label">Bucket所属地区</div>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="qcloudRegion" name="qcloudRegion" placeholder="如：sh（可选值 ，华南：gz 华北：tj 华东：sh）"/>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-2 control-label"></div>
                <input type="button" class="btn btn-primary" onclick="saveOrUpdate()" value="确定"/>
                &nbsp;&nbsp;<input type="button" class="btn btn-warning" onclick="reload()" value="返回"/>
            </div>
        </form>
    </div>

<script src="/static/js/oss.js"></script>
</body>
</html>