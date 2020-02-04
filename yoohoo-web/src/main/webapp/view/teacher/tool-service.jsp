<%@ page pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath }/src/student/css/tool-service.css?version=${_dt}">
<div id="toolApp">
    <div class="contact-us-menu">
        <div v-show="showUs">
            <span class="contact-us-content">
                    0571-28284628(客服)
                    <i></i>
            </span>
        </div>
        <div class="contact-us" @click="showUs = !showUs"></div>
    </div>
    <div class="qq-service-menu">
        <a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=3151993009&site=qq&menu=yes">
            <div class="qq-service"></div>
        </a>
    </div>
    <div   class="pay-service-menu">
            <div class="pay-service"  @click="pay()">  </div>
    </div>
</div>
<script>
    var toolApp = new Vue({
        el: '#toolApp',
        data: function(){
            return {
                showUs: false,
                showQQ: false
            }
        },
        created: function () {

        },
        methods: {
            contactQQ: function () {
                this.showQQ = !this.showQQ
            },
            pay: function () {
                layer.open({
                    type: 2,
                    title: '充值页面',
                    maxmin: true,
                    shadeClose: true, //点击遮罩关闭层
                    area: ['900px', '800px'],
                    content: '/view/student/recharge.jsp'
                });
            }
        }
    });
</script>