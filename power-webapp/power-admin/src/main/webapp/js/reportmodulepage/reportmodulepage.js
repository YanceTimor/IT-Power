$(function () {
    $("#jqGrid").jqGrid({
        url: '../reportmodulepage/list',     // 请求后台json数据的url
        datatype: "json",                    // 后台返回的数据格式
        // 列表标题及列表模型
        colModel: [
            {label: 'id', name: 'id', index: 'id', width: 50, key: true, hidden: true},
            {label: '编码', name: 'code', index: 'code', width: 120},
            {
                label: '专题类型',
                name: 'pageType',
                index: 'page_type',
                width: 80,
                formatter: function (value, options, row) {
                    return value === 0 ?
                        'PC' :
                        'APP';
                }
            },
            {label: '标题', name: 'title', index: 'title', width: 80},
            {
                label: '状态', name: 'status', index: 'status', width: 80,
                formatter: function (value, options, row) {
                    return value === 1 ?
                        '<span class="label label-warning">新草稿</span>' :
                        '<span class="label label-info">已发布</span>';
                }
            },
            {
                label: '开关', name: 'disabled', index: 'statdisabledus', width: 80,
                formatter: function (value, options, row) {
                    return value === 0 ?
                        '<span class="label label-success">启用</span>' :
                        '<span class="label label-danger">禁用</span>';
                }
            },
        ],
        viewrecords: true,     // 是否显示行号，默认值是false，不显示
        height: 385,            // 表格高度
        rowNum: 50,             // 一页显示的行记录数
        rowList: [50, 100],     // 翻页控制条中 每页显示记录数可选集合
        rownumbers: true,
        rownumWidth: 25,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",          // 翻页DOM
        jsonReader: {                   // 重写后台返回数据的属性
            root: "page.list",          // 将rows修改为page.list
            page: "page.currPage",      // 将page修改为page.currPage
            total: "page.totalPage",    // 将total修改为page.totalPage
            records: "page.totalCount"  // 将records修改为page.totalCount
        },
        prmNames: {              // 改写请求参数属性
            page: "page",
            rows: "limit",
            order: "order"
        },
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
            //设置高度
            $("#jqGrid").jqGrid('setGridHeight', getWinH());
        }
    });
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        reportModulePage: {}
    },
    methods: {
        query: function () {
            //vm.reload();
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {
                    keyword: $("#keyword").val()
                },
                page: 1
            }).trigger("reloadGrid");
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.reportModulePage = {};
        },
        update: function (event) {
            var id = getSelectedRow();
            if (id == null) {
                return;
            }
            vm.showList = false;
            vm.title = "修改";

            vm.getInfo(id)
        },
        saveOrUpdate: function (event) {
            var url = vm.reportModulePage.id == null ? "../reportmodulepage/save" : "../reportmodulepage/update";
            $.ajax({
                type: "POST",
                url: url,
                data: JSON.stringify(vm.reportModulePage),
                success: function (r) {
                    if (getStringValue(r.reportModulePage.id) != "") {
                        vm.reportModulePage.id = r.reportModulePage.id;
                        vm.reportModulePage.code = r.reportModulePage.code;
                    }
                    if (r.code === 0) {
                        alert('操作成功', function (index) {
                            vm.reload();
                        });
                    } else {
                        alert(r.msg);
                    }
                }
            });
        },
        del: function (event) {
            var codes = getSelectedRowsCodes();
            if (codes == null) {
                return;
            }

            confirm('确定要删除选中的记录？', function () {
                $.ajax({
                    type: "POST",
                    url: "../reportmodulepage/delete",
                    data: JSON.stringify(codes),
                    success: function (r) {
                        if (r.code == 0) {
                            alert('操作成功', function (index) {
                                $("#jqGrid").trigger("reloadGrid");
                            });
                        } else {
                            alert(r.msg);
                        }
                    }
                });
            });
        },
        getInfo: function (id) {
            $.get("../reportmodulepage/info/" + id, function (r) {
                vm.reportModulePage = r.reportModulePage;
            });
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                page: page
            }).trigger("reloadGrid");
        },
        addModule: function () {
            window.location.href = "report_module.html?id=";
        },
        updateModule: function (event) {
            var id = getSelectedRow();
            if (id == null) {
                return;
            }
            window.location.href = "report_module.html?id=" + id;
        },
    }
});


//选择多条记录
function getSelectedRowsCodes() {
    var grid = $("#jqGrid");
    var rowKey = grid.getGridParam("selrow");
    if (!rowKey) {
        alert("请选择一条记录");
        return;
    }

    var rowdata;
    var codes = [];
    var selarrrow = grid.getGridParam("selarrrow");
    $.each(selarrrow, function (n, value) {
        rowdata = $("#jqGrid").jqGrid('getRowData', value);
        codes[n] = rowdata['code'];//列名
    });
    console.info(codes);
    return codes;
}