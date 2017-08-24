/*
 * e.preventDefault();//阻止元素发生默认的行为(例如,当点击提交按钮时阻止对表单的提交
 * <!-- 图表控件 张海 walk_hai@163.com-->
 */

/*
 * 横幅标题栏控件 banner
 * acc  是 class="component" 的DIV
 * e 是 class="leipiplugins" 的控件
 */
LPB.plugins['banner'] = function (active_component, leipiplugins) {
    var popover = $(".popover");
    var jsonStr = $(leipiplugins).val();
    var jsonObj = {
        "type": "banner",
        "name": "",
        "config": {
            "title": "",
            "subtitle": "",
            "date": "",
            "info": ""
        }
    };
    if (getStringValue(jsonStr) != "") {
        jsonObj = JSON.parse($(leipiplugins).val());
        //右弹form  初始化值
        $(popover).find("#orgname").val(jsonObj.name);
        if (jsonObj.config.title !== undefined) {
            $(popover).find("#banner_config_title").val(jsonObj.config.title);
        }
        if (jsonObj.config.subtitle !== undefined) {
            $(popover).find("#banner_config_subtitle").val(jsonObj.config.subtitle);
        }
        if (jsonObj.config.date !== undefined) {
            $(popover).find("#banner_config_date").val(jsonObj.config.date);
        }
        if (jsonObj.config.info !== undefined) {
            $(popover).find("#banner_config_info").val(jsonObj.config.info);
        }
    }
    // 右弹form  取消控件
    $(popover).delegate(".btn-danger", "click", function (e) {
        active_component.popover("hide");
    });
    // 右弹form  确定控件
    $(popover).delegate(".btn-info", "click", function (e) {

        var inputs = $(popover).find("input");
        $.each(inputs, function (i, e) {
            var attr_name = $(e).attr("id");//属性名称
            var attr_val = $(e).val();
            switch (attr_name) {
                case 'banner_config_title':
                    jsonObj.config.title = attr_val;
                    break;
                case 'banner_config_subtitle':
                    jsonObj.config.subtitle = attr_val;
                    break;
                case 'banner_config_info':
                    jsonObj.config.info = attr_val;
                    break;
                case 'orgname':
                    attr_val = getAttrVal(attr_val, "banner");
                    jsonObj.name = attr_val;
                    active_component.find(".leipiplugins-orgname").html(attr_val);
                    break;
            }
            active_component.popover("hide");
            LPB.genSource();// 重置源代码
        });
        jsonObj.config.date = $("#banner_config_date").val();

        setLeipipluginsVal(leipiplugins, jsonObj);
    });
}


/*
 * 信息栏控件 info
 * acc  是 class="component" 的DIV
 * e 是 class="leipiplugins" 的控件
 */
LPB.plugins['info'] = function (active_component, leipiplugins) {
    var popover = $(".popover");
    var jsonStr = $(leipiplugins).val();
    var jsonObj = {
        "type": "info",
        "name": "",
        "config": {
            "text": "",
            "dataUrl": ""
        }
    };
    if (getStringValue(jsonStr) != "") {
        jsonObj = JSON.parse($(leipiplugins).val());
        //右弹form  初始化值
        $(popover).find("#orgname").val(jsonObj.name);
        if (jsonObj.config.text !== undefined) {
            $(popover).find("#info_config_text").val(jsonObj.config.text);
        }
    }
    // 加载数据源
    forSelectOption("#info_config_data_url", jsonObj.config.dataUrl);

    // 右弹form  取消控件
    $(popover).delegate(".btn-danger", "click", function (e) {
        active_component.popover("hide");
    });
    // 右弹form  确定控件
    $(popover).delegate(".btn-info", "click", function (e) {
        jsonObj.config.dataUrl = $("#info_config_data_url").val();
        var inputs = $(popover).find("input");
        $.each(inputs, function (i, e) {
            var attr_name = $(e).attr("id");//属性名称
            var attr_val = $(e).val();
            switch (attr_name) {
                case 'info_config_text':
                    jsonObj.config.text = attr_val;
                    break;
                case 'orgname':
                    attr_val = getAttrVal(attr_val, "info");
                    jsonObj.name = attr_val;
                    active_component.find(".leipiplugins-orgname").html(attr_val);
                    break;
            }
            active_component.popover("hide");
            LPB.genSource();// 重置源代码
        });
        setLeipipluginsVal(leipiplugins, jsonObj);
    });
}


/*
 * 折线图控件 chart_line
 * acc  是 class="component" 的DIV
 * e 是 class="leipiplugins" 的控件
 */
LPB.plugins['chart_line'] = function (active_component, leipiplugins) {
    var popover = $(".popover");
    var jsonStr = $(leipiplugins).val();
    var jsonObj = {
        "type": "chart_line",
        "name": "",
        "config": {
            "chart_type": "line-or-bar",
            "title": "no-set",
            "dataUrl": "",
            "legend": [],
            "seriesName": [],
            "xAxis": {
                "value": "",
                "name": ""
            },
            "yAxis": {
                "type": "value",
                "name": ""
            },
            "series": [
                {
                    "name": "",
                    "type": "line",
                    "data": []
                }
            ]
        }
    };

    if (getStringValue(jsonStr) != "") {
        jsonObj = JSON.parse($(leipiplugins).val());
        // 右弹form  初始化值
        $(popover).find("#orgname").val(jsonObj.name);
        if (jsonObj.config.title !== undefined) {
            $(popover).find("#chart_line_config_title").val(jsonObj.config.title);
        }
        if (jsonObj.config.legend !== undefined) {
            $(popover).find("#chart_line_config_legend").val(arrayToString(jsonObj.config.legend));
        }
        if (jsonObj.config.seriesName !== undefined) {
            $(popover).find("#chart_line_config_seriesName").val(arrayToString(jsonObj.config.seriesName));
        }
        if (jsonObj.config.yAxis.name !== undefined) {
            $(popover).find("#chart_line_config_yAxis_name").val(jsonObj.config.yAxis.name);
        }
        if (jsonObj.config.xAxis.name !== undefined) {
            $(popover).find("#chart_line_config_xAxis_name").val(jsonObj.config.xAxis.name);
        }
        if (jsonObj.config.xAxis.value !== undefined) {
            $(popover).find("#chart_line_config_xAxis_value").val(jsonObj.config.xAxis.value);
        }
    }
    // 加载数据源
    forSelectOption("#chart_line_config_data_url", jsonObj.config.dataUrl);
    // 右弹form  取消控件
    $(popover).delegate(".btn-danger", "click", function (e) {
        active_component.popover("hide");
    });
    // 右弹form  确定控件
    $(popover).delegate(".btn-info", "click", function (e) {
        jsonObj.config.dataUrl = $("#chart_line_config_data_url").val();
        var inputs = $(popover).find("input");
        $.each(inputs, function (i, e) {
            var attr_name = $(e).attr("id");// 属性名称
            var attr_val = $(e).val();
            switch (attr_name) {
                case 'chart_line_config_title':
                    jsonObj.config.title = attr_val;
                    break;
                case 'chart_line_config_legend':
                    jsonObj.config.legend = stringToArray(attr_val);
                    break;
                case 'chart_line_config_seriesName':
                    jsonObj.config.seriesName = stringToArray(attr_val);
                    break;
                case 'chart_line_config_yAxis_name':
                    jsonObj.config.yAxis.name = attr_val;
                    break;
                case 'chart_line_config_xAxis_name':
                    jsonObj.config.xAxis.name = attr_val;
                    break;
                case 'chart_line_config_xAxis_value':
                    jsonObj.config.xAxis.value = attr_val;
                    break;
                case 'orgname':
                    attr_val = getAttrVal(attr_val, "chart_line");
                    jsonObj.name = attr_val;
                    active_component.find(".leipiplugins-orgname").html(attr_val);
                    break;
            }
            active_component.popover("hide");
            LPB.genSource();//重置源代码
        });
        setLeipipluginsVal(leipiplugins, jsonObj);
    });
}


/*
 * 页签表格tab切换 tables_v3
 * acc  是 class="component" 的DIV
 * e 是 class="leipiplugins" 的控件
 */
LPB.plugins['tables_v3'] = function (active_component, leipiplugins) {
    var popover = $(".popover");
    var jsonStr = $(leipiplugins).val();
    var jsonObj = {
        "type": "tables_v3",
        "name": "",
        "config": []
    };
    var jsonConfigObj;
    var document;
    // 右弹form  初始化值
    if (getStringValue(jsonStr) != "") {
        jsonObj = JSON.parse($(leipiplugins).val());
        $(popover).find("#orgname").val(jsonObj.name);
        if (jsonObj.config.length > 0) {
            $.each(jsonObj.config, function (n, value) {
                // 创建tab
                document = addTab();
                if (value.title !== undefined) {
                    $("#a_" + document).html(value.title);
                    $(popover).find("#tables_v3_config_title_" + document).val(value.title);
                }
                if (value.levelParameter !== undefined) {
                    $(popover).find("#tables_v3_config_level_parameter_" + document).val(value.levelParameter);
                }
                if (value.table.head !== undefined) {
                    var headerStr = "";
                    for (var p in value.table.head) {
                        headerStr = headerStr + value.table.head[p]["title"] + "=" + value.table.head[p]["value"] + "\n";
                    }
                    headerStr = headerStr.substr(0, headerStr.length - 1);
                    $(popover).find("#tables_v3_config_table_head_" + document).val(headerStr);
                }
                // 加载数据源
                forSelectOption("#tables_v3_config_data_url_" + document, value.dataUrl);
                // 初始化下钻页面
                initLevelTab(document, value.levelCnt, value.level);
                // 联动下钻tab
                changeLevelTab(document);
            });
        } else {
            // 创建tab
            addTab();
        }
    } else {
        // 创建tab
        addTab();
    }
    // 初始化tab绑定事件
    tabsEvent();
    // 初始化tab下钻页面绑定事件
    tabsLevelEvent();

    // 右弹form  取消控件
    $(popover).delegate(".btn-danger", "click", function (e) {
        active_component.popover("hide");
    });
    // 右弹form  确定控件
    $(popover).delegate(".btn-info", "click", function (e) {
        // 获取控件名称
        var inputs = $(popover).find("input");
        $.each(inputs, function (i, e) {
            var attr_name = $(e).attr("id");// 属性名称
            var attr_val = $(e).val();
            switch (attr_name) {
                case 'orgname':
                    attr_val = getAttrVal(attr_val, "tables_v3");
                    jsonObj.name = attr_val;
                    active_component.find(".leipiplugins-orgname").html(attr_val);
                    break;
            }
        });

        // 清空config数组
        jsonObj.config = [];
        var tabDocuments = $(".tab_document");
        // 遍历所有tab div
        $.each(tabDocuments, function (j, tabDoc) {
            document = $(tabDoc).attr("document");//tab_div 随机码
            jsonConfigObj = {
                "title": "",
                "dataUrl": "",
                "table": {
                    "head": []
                },
                "levelCnt": 0,
                "level": {},
                "levelParameter": ""
            };

            var headStr = $('#tables_v3_config_table_head_' + document).val();
            if (getStringValue(headStr) != "") {
                var headArr = headStr.replace(/\n/g, "|").split("|");
                var headJsonArr = [];
                $.each(headArr, function (i, value) {
                    var headJson = {};
                    headJson["title"] = value.split("=")[0];
                    headJson["value"] = value.split("=")[1];
                    headJsonArr[i] = headJson;
                });
                jsonConfigObj.table.head = headJsonArr;
            }

            // 遍历 所有input内容
            var inputs = $(tabDoc).find("input");
            var nameString = "";
            jsonConfigObj.dataUrl = $('#tables_v3_config_data_url_' + document).val();
            $.each(inputs, function (i, e) {
                var attr_name = $(e).attr("id");// 属性名称
                var attr_val = $(e).val();
                switch (attr_name) {
                    case 'tables_v3_config_title_' + document:
                        nameString = nameString + "_" + attr_val;
                        jsonConfigObj.title = attr_val;
                        break;
                    case 'tables_v3_config_level_parameter_' + document:
                        jsonConfigObj.levelParameter = attr_val;
                        break;
                }
                active_component.popover("hide");
                LPB.genSource();    // 重置源代码
            });
            jsonConfigObj = packageLevelTab(jsonConfigObj, document);
            jsonObj.config[j] = jsonConfigObj;
        });
        setLeipipluginsVal(leipiplugins, jsonObj);
        // 删除 tab div
        $("#content div").html("");
    });
}

/**
 * 初始化渲染下钻页面
 * @param levelObj
 */
function initLevelTab(document, levelCnt, levelObj) {
    if (levelCnt > 0) {
        var popover = $(".popover");
        var document_level;
        $.each(levelObj, function (n, level) {
            // 创建下钻tab
            document_level = addTabLevel(document);
            if (level.title !== undefined) {
                $("#a_" + document_level).html(level.title);
                $(popover).find("#tables_v3_config_title_" + document_level).val(level.title);
            }
            if (level.levelParameter !== undefined) {
                $(popover).find("#tables_v3_config_level_parameter_next_" + document_level).val(level.levelParameter);
            }
            if (level.table.head !== undefined) {
                var headerStr = "";
                for (var p in level.table.head) {
                    headerStr = headerStr + level.table.head[p]["title"] + "=" + level.table.head[p]["value"] + "\n";
                }
                headerStr = headerStr.substr(0, headerStr.length - 1);
                $(popover).find("#tables_v3_config_table_head_" + document_level).val(headerStr);
            }
            // 加载数据源
            forSelectOption("#tables_v3_config_data_url_" + document_level, level.dataUrl);
        });
    }
}

/**
 * 组装下钻页面
 */
function packageLevelTab(jsonConfigObj, document) {
    var levelObj;
    var tabDocuments = $(".tab_document_level");
    var document_level;
    var levelCnt = 0;
    // 遍历所有tab div
    $.each(tabDocuments, function (j, tabDoc) {
        if (document == $(tabDoc).attr("document")) {
            levelCnt = levelCnt + 1;
            document_level = $(tabDoc).attr("document_level");//tab_div 随机码
            levelObj = {
                "title": "",
                "dataUrl": "",
                "table": {
                    "head": []
                },
                "levelParameter": ""
            };

            var headStr = $('#tables_v3_config_table_head_' + document_level).val();
            if (getStringValue(headStr) != "") {
                var headArr = headStr.replace(/\n/g, "|").split("|");
                var headJsonArr = [];
                $.each(headArr, function (i, value) {
                    var headJson = {};
                    headJson["title"] = value.split("=")[0];
                    headJson["value"] = value.split("=")[1];
                    headJsonArr[i] = headJson;
                });
                levelObj.table.head = headJsonArr;
            }

            // 遍历 所有input内容
            var inputs = $(tabDoc).find("input");
            var nameString = "";
            levelObj.dataUrl = $('#tables_v3_config_data_url_' + document_level).val();
            $.each(inputs, function (i, e) {
                var attr_name = $(e).attr("id");// 属性名称
                var attr_val = $(e).val();
                switch (attr_name) {
                    case 'tables_v3_config_title_' + document_level:
                        nameString = nameString + "_" + attr_val;
                        levelObj.title = attr_val;
                        break;
                    case 'tables_v3_config_level_parameter_next_' + document_level:
                        levelObj.levelParameter = attr_val;
                        break;
                }
            });
            console.info(JSON.stringify(levelObj));
            jsonConfigObj.level["level_" + levelCnt] = levelObj;
        }
    });
    jsonConfigObj.levelCnt = levelCnt;
    return jsonConfigObj;
}

/**
 * 初始化页签tabs绑定事件
 */
function tabsEvent() {
    var contentname;
    $('#tabs').on('click', '.tab', function () {
        var this_document = $(this).attr("document");
        contentname = this_document + "_content";
        $(".tab_document").hide();
        $("#tabs li").removeClass("current");
        $("#" + contentname).show();
        $(this).parent().addClass("current");
        // 联动下钻tab
        changeLevelTab(this_document);
        // 保持高度
        keepWindowHeight();
    });

    $('#tabs').on('click', '.remove', function () {
        var document = $(this).parent().find(".tab").attr("document");
        contentname = document + "_content";
        $("#" + contentname).remove();
        $(this).parent().remove();
        // 联动删除下钻tab
        changeLevelTabRemove(document);
        if ($("#tabs li.current").length == 0 && $("#tabs li").length > 0) {
            var firstTab = $("#tabs li:first-child");
            firstTab.addClass("current");
            var firstTabDocument = $(firstTab).find("a.tab").attr("document");
            $("#" + firstTabDocument + "_content").show();
            // 联动下钻tab
            changeLevelTab(firstTabDocument);
        }
    });
};

/**
 * 下钻tab联动
 * @param this_document
 */
function changeLevelTab(this_document) {
    // 隐藏于显示
    $("#wrapper_level").hide();
    $(".tab_document_level").hide();
    $(".li_document_level").hide();
    $("#tabs_level li").removeClass("current");

    // 遍历所有下钻tab-li
    var firstLiId;
    $.each($(".li_document_level"), function (j, liDoc) {
        if (this_document == $(liDoc).attr("document")) {
            $("#" + liDoc.id).show();
            if (getStringValue(firstLiId) == "") {
                firstLiId = liDoc.id;
            }
        }
    });

    // 遍历所有下钻tab
    $.each($(".tab_document_level"), function (j, tabDoc) {
        if (this_document == $(tabDoc).attr("document")) {
            $("#" + tabDoc.id).show();
            // 显示下钻tab
            $("#wrapper_level").show();
            $("#" + firstLiId).addClass("current");
            return false;
        }
    });

}

/**
 * 下钻tab联动删除
 * @param this_document
 */
function changeLevelTabRemove(this_document) {
    $.each($("li[document='" + this_document + "']"), function (i, liDoc) {
        liDoc.remove();
    });
    $.each($("div[document='" + this_document + "']"), function (j, divDoc) {
        divDoc.remove();
    });
}

/**
 * 初始化页签下钻页面tabs绑定事件
 */
function tabsLevelEvent() {
    var contentname;
    $('#tabs_level').on('click', '.tab', function () {
        contentname = $(this).attr("document_level") + "_content";
        $(".tab_document_level").hide();
        $("#tabs_level li").removeClass("current");
        $("#" + contentname).show();
        $(this).parent().addClass("current");
        // 保持高度
        keepWindowHeight();
    });
    $('#tabs_level').on('click', '.remove', function () {
        var document = $(this).parent().find(".tab").attr("document");
        var document_level = $(this).parent().find(".tab").attr("document_level");
        contentname = document_level + "_content";
        $("#" + contentname).remove();
        $(this).parent().remove();
        changeLevelTab(document);
    });
};

/**
 * 页签tab控件添加标签
 * @returns {string}
 */
function addTab() {
    // 隐藏下钻tab
    $("#wrapper_level").hide();
    var document = "document_" + RndNum(10);

    $("#tabs li").removeClass("current");
    // 隐藏所有tab div
    $(".tab_document").hide();

    $("#tabs").append("<li class='current' id='li_" + document + "'><a class='tab' document='" + document + "' id='a_" + document + "' href='#'>"
        + "标题"
        + "</a><a href='#' class='remove'>x</a></li>");

    $("#content").append(
        "<div class='tab_document content-label-input' document='" + document + "' id='" + document + "_content'>"
        + "<label class='control-label content-left'>标题</label>"
        + "<input type='text'class='tables_v3_config_title content-left' document='" + document + "' id='tables_v3_config_title_" + document + "' placeholder='标题' onchange='onchangeTabTitle(this)'>"
        + "<label class='control-label content-left'>数据源</label>"
        + "<select id='tables_v3_config_data_url_" + document + "' class='selectpicker form-control content-left' data-live-search='true' title='数据源'></select>"
        + "<label class='control-label content-left'>表头</label>"
        + "<textarea class='tables_v3_config_table_head content-left' document='" + document + "' id='tables_v3_config_table_head_" + document + "' placeholder='表头'></textarea>"
        + "<label class='control-label content-left'>下钻参数</label>"
        + "<input type='text'class='tables_v3_config_level_parameter content-left' document='" + document + "' id='tables_v3_config_level_parameter_" + document + "' placeholder='下钻参数'>"
        + "<label class='add_tab_level' onclick='addTabLevel(\"" + document + "\")'> + 下钻页面</label>"
        + "</div>"
    );

    // 加载数据源
    forSelectOption("#tables_v3_config_data_url_" + document, "");

    // 显示新增tab div
    $("#" + document + "_content").show();

    // textarea 高度自动扩展
    // autosize($('textarea'));

    return document;
}

/**
 * 页签tab控件添加下钻页面
 * @returns {string}
 */
function addTabLevel(document) {
    // 显示下钻tab
    $("#wrapper_level").show();
    var document_level = "document_level_" + RndNum(10);

    $("#tabs_level li").removeClass("current");
    // 隐藏所有tab div
    $(".tab_document_level").hide();

    $("#tabs_level").append(
        "<li class='li_document_level current' document='" + document + "' id='li_" + document_level + "'>"
        + "<a class='tab' document='" + document + "' document_level='" + document_level + "' id='a_" + document_level + "' href='#'>"
        + "标题"
        + "</a>"
        + "<a href='#' class='remove'>x</a>"
        + "</li>"
    );

    $("#content_level").append(
        "<div class='tab_document_level content-label-input' document='" + document + "' document_level='" + document_level + "' id='" + document_level + "_content'>"
        + "<label class='control-label content-left'>标题</label>"
        + "<input type='text'class='tables_v3_config_title content-left' document_level='" + document_level + "' id='tables_v3_config_title_" + document_level + "' placeholder='标题' onchange='onchangeLevelTabTitle(this)'>"
        + "<label class='control-label content-left'>数据源</label>"
        + "<select id='tables_v3_config_data_url_" + document_level + "' class='selectpicker form-control content-left' data-live-search='true' title='数据源'></select>"
        + "<label class='control-label content-left'>表头</label>"
        + "<textarea class='tables_v3_config_table_head content-left' document_level='" + document_level + "' id='tables_v3_config_table_head_" + document_level + "' placeholder='表头'></textarea>"
        + "<label class='control-label content-left'>下钻参数</label>"
        + "<input type='text'class='tables_v3_config_level_parameter_next content-left' document='" + document + "' id='tables_v3_config_level_parameter_next_" + document_level + "' placeholder='下钻参数'>"
        + "</div>"
    );

    // 加载数据源
    forSelectOption("#tables_v3_config_data_url_" + document_level, "");

    // 显示新增tab div
    $("#" + document_level + "_content").show();
    return document_level;
}

/**
 * 修改页签tab标题
 * @param obj
 */
function onchangeTabTitle(obj) {
    $("#a_" + $(obj).attr("document")).html($(obj).val());
}

/**
 * 修改页签下钻tab标题
 * @param obj
 */
function onchangeLevelTabTitle(obj) {
    $("#a_" + $(obj).attr("document_level")).html($(obj).val());
}

/**
 * 生成的json赋给控件
 * @param leipiplugins
 * @param jsonObj
 */
function setLeipipluginsVal(leipiplugins, jsonObj) {
    $(leipiplugins).val(JSON.stringify(jsonObj));
    $(leipiplugins).text(JSON.stringify(jsonObj));
    $(leipiplugins).html(JSON.stringify(jsonObj));
}

/**
 * 获取控件名称
 * @param attr_val
 * @param attr_name
 * @returns {*}
 */
function getAttrVal(attr_val, attr_name) {
    if (getStringValue(attr_val) != "") {
        return attr_val;
    }
    return $("#" + attr_name).attr("title");
}

/**
 * 保持高度
 */
function keepWindowHeight() {
    var sc = $(window).scrollTop();
    $('body,html').animate({scrollTop: sc}, sc);
}
