(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2934a4da"],{"0adb":function(t,e,n){"use strict";n.d(e,"c",function(){return a}),n.d(e,"f",function(){return r}),n.d(e,"g",function(){return o}),n.d(e,"b",function(){return s}),n.d(e,"e",function(){return l}),n.d(e,"d",function(){return u}),n.d(e,"a",function(){return c});var i=n("b775");function a(t){return Object(i["a"])({url:"/repairs/repairsList",method:"post",data:t,headers:{"Content-Type":"application/json"}})}function r(){return Object(i["a"])({url:"/repairs/selectStatusList",method:"get"})}function o(t){return Object(i["a"])({url:"/repairs/updateRepair",method:"post",data:t,headers:{"Content-Type":"application/json"}})}function s(t){return Object(i["a"])({url:"/file/delImage/"+t,method:"get"})}function l(t){return Object(i["a"])({url:"/file/getImages/"+t,method:"get"})}function u(){return Object(i["a"])({url:"/file/getHostAddress",method:"get"})}function c(t){return Object(i["a"])({url:"/repairs/insertRepairAndFile",method:"post",data:t,headers:{"Content-Type":"application/json"}})}},1169:function(t,e,n){var i=n("2d95");t.exports=Array.isArray||function(t){return"Array"==i(t)}},"333d":function(t,e,n){"use strict";var i=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"pagination-container",class:{hidden:t.hidden}},[n("el-pagination",t._b({attrs:{background:t.background,"current-page":t.currentPage,"page-size":t.pageSize,layout:t.layout,"page-sizes":t.pageSizes,total:t.total},on:{"update:currentPage":function(e){t.currentPage=e},"update:current-page":function(e){t.currentPage=e},"update:pageSize":function(e){t.pageSize=e},"update:page-size":function(e){t.pageSize=e},"size-change":t.handleSizeChange,"current-change":t.handleCurrentChange}},"el-pagination",t.$attrs,!1))],1)},a=[];n("c5f6");Math.easeInOutQuad=function(t,e,n,i){return t/=i/2,t<1?n/2*t*t+e:(t--,-n/2*(t*(t-2)-1)+e)};var r=function(){return window.requestAnimationFrame||window.webkitRequestAnimationFrame||window.mozRequestAnimationFrame||function(t){window.setTimeout(t,1e3/60)}}();function o(t){document.documentElement.scrollTop=t,document.body.parentNode.scrollTop=t,document.body.scrollTop=t}function s(){return document.documentElement.scrollTop||document.body.parentNode.scrollTop||document.body.scrollTop}function l(t,e,n){var i=s(),a=t-i,l=20,u=0;e="undefined"===typeof e?500:e;var c=function t(){u+=l;var s=Math.easeInOutQuad(u,i,a,e);o(s),u<e?r(t):n&&"function"===typeof n&&n()};c()}var u={name:"Pagination",props:{total:{required:!0,type:Number},page:{type:Number,default:1},limit:{type:Number,default:20},pageSizes:{type:Array,default:function(){return[5,10,20,30,50,100]}},layout:{type:String,default:"total, sizes, prev, pager, next, jumper"},background:{type:Boolean,default:!0},autoScroll:{type:Boolean,default:!0},hidden:{type:Boolean,default:!1}},computed:{currentPage:{get:function(){return this.page},set:function(t){this.$emit("update:page",t)}},pageSize:{get:function(){return this.limit},set:function(t){this.$emit("update:limit",t)}}},methods:{handleSizeChange:function(t){this.$emit("pagination",{page:this.currentPage,limit:t}),this.autoScroll&&l(0,800)},handleCurrentChange:function(t){this.$emit("pagination",{page:t,limit:this.pageSize}),this.autoScroll&&l(0,800)}}},c=u,d=(n("f77d"),n("2877")),f=Object(d["a"])(c,i,a,!1,null,"4204e9a1",null);e["a"]=f.exports},"37c8":function(t,e,n){e.f=n("2b4c")},"3a72":function(t,e,n){var i=n("7726"),a=n("8378"),r=n("2d00"),o=n("37c8"),s=n("86cc").f;t.exports=function(t){var e=a.Symbol||(a.Symbol=r?{}:i.Symbol||{});"_"==t.charAt(0)||t in e||s(e,t,{value:o.f(t)})}},4381:function(t,e,n){"use strict";n("6762"),n("2fdb");var i=n("4360"),a={inserted:function(t,e,n){var a=e.value,r=i["a"].getters&&i["a"].getters.roles;if(!(a&&a instanceof Array&&a.length>0))throw new Error("need roles! Like v-permission=\"['admin','editor']\"");var o=a,s=r.some(function(t){return o.includes(t)});s||t.parentNode&&t.parentNode.removeChild(t)}},r=function(t){t.directive("permission",a)};window.Vue&&(window["permission"]=a,Vue.use(r)),a.install=r;e["a"]=a},"4f3d":function(t,e,n){"use strict";n.d(e,"g",function(){return a}),n.d(e,"f",function(){return r}),n.d(e,"c",function(){return o}),n.d(e,"d",function(){return s}),n.d(e,"b",function(){return l}),n.d(e,"e",function(){return u}),n.d(e,"a",function(){return c});var i=n("b775");function a(t){return Object(i["a"])({url:"/user/selectUserByName",method:"post",data:t,headers:{"Content-Type":"application/json"}})}function r(t){return Object(i["a"])({url:"/user/selectUserById",method:"post",data:t,headers:{"Content-Type":"application/json"}})}function o(t){return Object(i["a"])({url:"/user/userList",method:"post",data:t,headers:{"Content-Type":"application/json"}})}function s(){return Object(i["a"])({url:"/user/selectIdTypeList",method:"get"})}function l(t){return Object(i["a"])({url:"/user/deleteUserById",method:"post",data:t,headers:{"Content-Type":"application/json"}})}function u(t){return Object(i["a"])({url:"/user/updateUserById",method:"post",data:t,headers:{"Content-Type":"application/json"}})}function c(t){return Object(i["a"])({url:"/user/createUser",method:"post",data:t,headers:{"Content-Type":"application/json"}})}},6017:function(t,e,n){},6724:function(t,e,n){"use strict";n("8d41");var i="@@wavesContext";function a(t,e){function n(n){var i=Object.assign({},e.value),a=Object.assign({ele:t,type:"hit",color:"rgba(0, 0, 0, 0.15)"},i),r=a.ele;if(r){r.style.position="relative",r.style.overflow="hidden";var o=r.getBoundingClientRect(),s=r.querySelector(".waves-ripple");switch(s?s.className="waves-ripple":(s=document.createElement("span"),s.className="waves-ripple",s.style.height=s.style.width=Math.max(o.width,o.height)+"px",r.appendChild(s)),a.type){case"center":s.style.top=o.height/2-s.offsetHeight/2+"px",s.style.left=o.width/2-s.offsetWidth/2+"px";break;default:s.style.top=(n.pageY-o.top-s.offsetHeight/2-document.documentElement.scrollTop||document.body.scrollTop)+"px",s.style.left=(n.pageX-o.left-s.offsetWidth/2-document.documentElement.scrollLeft||document.body.scrollLeft)+"px"}return s.style.backgroundColor=a.color,s.className="waves-ripple z-active",!1}}return t[i]?t[i].removeHandle=n:t[i]={removeHandle:n},n}var r={bind:function(t,e){t.addEventListener("click",a(t,e),!1)},update:function(t,e){t.removeEventListener("click",t[i].removeHandle,!1),t.addEventListener("click",a(t,e),!1)},unbind:function(t){t.removeEventListener("click",t[i].removeHandle,!1),t[i]=null,delete t[i]}},o=function(t){t.directive("waves",r)};window.Vue&&(window.waves=r,Vue.use(o)),r.install=o;e["a"]=r},"67ab":function(t,e,n){var i=n("ca5a")("meta"),a=n("d3f4"),r=n("69a8"),o=n("86cc").f,s=0,l=Object.isExtensible||function(){return!0},u=!n("79e5")(function(){return l(Object.preventExtensions({}))}),c=function(t){o(t,i,{value:{i:"O"+ ++s,w:{}}})},d=function(t,e){if(!a(t))return"symbol"==typeof t?t:("string"==typeof t?"S":"P")+t;if(!r(t,i)){if(!l(t))return"F";if(!e)return"E";c(t)}return t[i].i},f=function(t,e){if(!r(t,i)){if(!l(t))return!0;if(!e)return!1;c(t)}return t[i].w},p=function(t){return u&&m.NEED&&l(t)&&!r(t,i)&&c(t),t},m=t.exports={KEY:i,NEED:!1,fastKey:d,getWeak:f,onFreeze:p}},"7a07":function(t,e,n){"use strict";n.d(e,"f",function(){return a}),n.d(e,"h",function(){return r}),n.d(e,"e",function(){return o}),n.d(e,"g",function(){return s}),n.d(e,"c",function(){return l}),n.d(e,"i",function(){return u}),n.d(e,"d",function(){return c}),n.d(e,"b",function(){return d}),n.d(e,"a",function(){return f});var i=n("b775");function a(t){return Object(i["a"])({url:"/apartment/apartmentList",method:"post",data:t,headers:{"Content-Type":"application/json"}})}function r(){return Object(i["a"])({url:"/apartment/selectPattern",method:"get"})}function o(){return Object(i["a"])({url:"/apartment/selectFaceList",method:"get"})}function s(){return Object(i["a"])({url:"/apartment/selectHouseType",method:"get"})}function l(t){return Object(i["a"])({url:"/apartment/createApartment",method:"post",data:t,headers:{"Content-Type":"application/json"}})}function u(t){return Object(i["a"])({url:"/apartment/updateApartment",method:"post",data:t,headers:{"Content-Type":"application/json"}})}function c(t){return Object(i["a"])({url:"/apartment/deleteApartment",method:"post",data:t,headers:{"Content-Type":"application/json"}})}function d(t){return Object(i["a"])({url:"/apartment/getApartmentListByUserid/"+t,method:"get"})}function f(t){return Object(i["a"])({url:"/apartment/getApartmentById/"+t,method:"get"})}},"7bbc":function(t,e,n){var i=n("6821"),a=n("9093").f,r={}.toString,o="object"==typeof window&&window&&Object.getOwnPropertyNames?Object.getOwnPropertyNames(window):[],s=function(t){try{return a(t)}catch(e){return o.slice()}};t.exports.f=function(t){return o&&"[object Window]"==r.call(t)?s(t):a(i(t))}},"7fb8":function(t,e,n){},"8a81":function(t,e,n){"use strict";var i=n("7726"),a=n("69a8"),r=n("9e1e"),o=n("5ca1"),s=n("2aba"),l=n("67ab").KEY,u=n("79e5"),c=n("5537"),d=n("7f20"),f=n("ca5a"),p=n("2b4c"),m=n("37c8"),h=n("3a72"),b=n("d4c0"),g=n("1169"),v=n("cb7c"),y=n("d3f4"),w=n("4bf8"),_=n("6821"),k=n("6a99"),S=n("4630"),O=n("2aeb"),j=n("7bbc"),L=n("11e9"),x=n("2621"),T=n("86cc"),C=n("0d58"),F=L.f,A=T.f,Q=j.f,E=i.Symbol,V=i.JSON,N=V&&V.stringify,P="prototype",D=p("_hidden"),z=p("toPrimitive"),I={}.propertyIsEnumerable,U=c("symbol-registry"),$=c("symbols"),B=c("op-symbols"),H=Object[P],W="function"==typeof E&&!!x.f,M=i.QObject,R=!M||!M[P]||!M[P].findChild,J=r&&u(function(){return 7!=O(A({},"a",{get:function(){return A(this,"a",{value:7}).a}})).a})?function(t,e,n){var i=F(H,e);i&&delete H[e],A(t,e,n),i&&t!==H&&A(H,e,i)}:A,q=function(t){var e=$[t]=O(E[P]);return e._k=t,e},K=W&&"symbol"==typeof E.iterator?function(t){return"symbol"==typeof t}:function(t){return t instanceof E},Y=function(t,e,n){return t===H&&Y(B,e,n),v(t),e=k(e,!0),v(n),a($,e)?(n.enumerable?(a(t,D)&&t[D][e]&&(t[D][e]=!1),n=O(n,{enumerable:S(0,!1)})):(a(t,D)||A(t,D,S(1,{})),t[D][e]=!0),J(t,e,n)):A(t,e,n)},G=function(t,e){v(t);var n,i=b(e=_(e)),a=0,r=i.length;while(r>a)Y(t,n=i[a++],e[n]);return t},X=function(t,e){return void 0===e?O(t):G(O(t),e)},Z=function(t){var e=I.call(this,t=k(t,!0));return!(this===H&&a($,t)&&!a(B,t))&&(!(e||!a(this,t)||!a($,t)||a(this,D)&&this[D][t])||e)},tt=function(t,e){if(t=_(t),e=k(e,!0),t!==H||!a($,e)||a(B,e)){var n=F(t,e);return!n||!a($,e)||a(t,D)&&t[D][e]||(n.enumerable=!0),n}},et=function(t){var e,n=Q(_(t)),i=[],r=0;while(n.length>r)a($,e=n[r++])||e==D||e==l||i.push(e);return i},nt=function(t){var e,n=t===H,i=Q(n?B:_(t)),r=[],o=0;while(i.length>o)!a($,e=i[o++])||n&&!a(H,e)||r.push($[e]);return r};W||(E=function(){if(this instanceof E)throw TypeError("Symbol is not a constructor!");var t=f(arguments.length>0?arguments[0]:void 0),e=function(n){this===H&&e.call(B,n),a(this,D)&&a(this[D],t)&&(this[D][t]=!1),J(this,t,S(1,n))};return r&&R&&J(H,t,{configurable:!0,set:e}),q(t)},s(E[P],"toString",function(){return this._k}),L.f=tt,T.f=Y,n("9093").f=j.f=et,n("52a7").f=Z,x.f=nt,r&&!n("2d00")&&s(H,"propertyIsEnumerable",Z,!0),m.f=function(t){return q(p(t))}),o(o.G+o.W+o.F*!W,{Symbol:E});for(var it="hasInstance,isConcatSpreadable,iterator,match,replace,search,species,split,toPrimitive,toStringTag,unscopables".split(","),at=0;it.length>at;)p(it[at++]);for(var rt=C(p.store),ot=0;rt.length>ot;)h(rt[ot++]);o(o.S+o.F*!W,"Symbol",{for:function(t){return a(U,t+="")?U[t]:U[t]=E(t)},keyFor:function(t){if(!K(t))throw TypeError(t+" is not a symbol!");for(var e in U)if(U[e]===t)return e},useSetter:function(){R=!0},useSimple:function(){R=!1}}),o(o.S+o.F*!W,"Object",{create:X,defineProperty:Y,defineProperties:G,getOwnPropertyDescriptor:tt,getOwnPropertyNames:et,getOwnPropertySymbols:nt});var st=u(function(){x.f(1)});o(o.S+o.F*st,"Object",{getOwnPropertySymbols:function(t){return x.f(w(t))}}),V&&o(o.S+o.F*(!W||u(function(){var t=E();return"[null]"!=N([t])||"{}"!=N({a:t})||"{}"!=N(Object(t))})),"JSON",{stringify:function(t){var e,n,i=[t],a=1;while(arguments.length>a)i.push(arguments[a++]);if(n=e=i[1],(y(e)||void 0!==t)&&!K(t))return g(e)||(e=function(t,e){if("function"==typeof n&&(e=n.call(this,t,e)),!K(e))return e}),i[1]=e,N.apply(V,i)}}),E[P][z]||n("32e9")(E[P],z,E[P].valueOf),d(E,"Symbol"),d(Math,"Math",!0),d(i.JSON,"JSON",!0)},"8d41":function(t,e,n){},"8e3a":function(t,e,n){"use strict";var i=n("7fb8"),a=n.n(i);a.a},"945b":function(t,e,n){"use strict";n.r(e);var i=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"app-container"},[n("div",{staticClass:"filter-container"},[n("el-date-picker",{attrs:{type:"datetimerange","picker-options":t.pickerOptions,"range-separator":"至","start-placeholder":"【创建】开始日期","end-placeholder":"【创建】结束日期"},on:{change:function(e){return t.resolveCreateLimit()}},nativeOn:{keyup:function(e){return!e.type.indexOf("key")&&t._k(e.keyCode,"enter",13,e.key,"Enter")?null:t.handleFilter(e)}},model:{value:t.createtimeLimit,callback:function(e){t.createtimeLimit=e},expression:"createtimeLimit"}}),t._v(" "),n("el-date-picker",{attrs:{type:"datetimerange","picker-options":t.pickerOptions,"range-separator":"至","start-placeholder":"【更新】开始日期","end-placeholder":"【更新】结束日期"},on:{change:function(e){return t.resolveUpdateLimit()}},nativeOn:{keyup:function(e){return!e.type.indexOf("key")&&t._k(e.keyCode,"enter",13,e.key,"Enter")?null:t.handleFilter(e)}},model:{value:t.updatetimeLimit,callback:function(e){t.updatetimeLimit=e},expression:"updatetimeLimit"}}),t._v(" "),n("el-input",{staticClass:"filter-item",staticStyle:{width:"150px"},attrs:{placeholder:"租户姓名"},nativeOn:{keyup:function(e){return!e.type.indexOf("key")&&t._k(e.keyCode,"enter",13,e.key,"Enter")?null:t.handleFilter(e)}},model:{value:t.listQuery.username,callback:function(e){t.$set(t.listQuery,"username","string"===typeof e?e.trim():e)},expression:"listQuery.username"}}),t._v(" "),n("el-button",{directives:[{name:"waves",rawName:"v-waves"}],staticClass:"filter-item",attrs:{type:"primary",icon:"el-icon-search"},on:{click:t.handleFilter}},[t._v("\n      搜索\n    ")])],1),t._v(" "),n("el-table",{directives:[{name:"loading",rawName:"v-loading",value:t.listLoading,expression:"listLoading"}],key:t.tableKey,staticStyle:{width:"100%"},attrs:{data:t.list,border:"",fit:"",stripe:"","highlight-current-row":""},on:{"filter-change":t.filterHanderChange}},[n("el-table-column",{attrs:{label:"ID",type:"index",align:"center",width:"60"}}),t._v(" "),t.checkPermission(["租户"])?t._e():n("el-table-column",{attrs:{label:"报修人",prop:"username",width:"110px",align:"center"},scopedSlots:t._u([{key:"default",fn:function(e){return[n("el-tooltip",{staticClass:"item",attrs:{effect:"light",content:"点击查看租户信息",placement:"right"}},[n("el-button",{attrs:{type:"text",size:"mini","close-transition":""},on:{click:function(n){return t.watchUserData(e.row)}}},[t._v(t._s(e.row.username))])],1)]}}],null,!1,4131146161)}),t._v(" "),n("el-table-column",{attrs:{label:"报修描述内容",prop:"content",align:"center"},scopedSlots:t._u([{key:"default",fn:function(e){return[n("span",[t._v(t._s(e.row.content))])]}}])}),t._v(" "),n("el-table-column",{attrs:{label:"报修公寓地址",prop:"apartmentAddress",align:"center"},scopedSlots:t._u([{key:"default",fn:function(e){return[n("el-tooltip",{staticClass:"item",attrs:{effect:"light",content:"点击查看公寓信息",placement:"top"}},[n("el-button",{attrs:{type:"text",size:"mini","close-transition":""},on:{click:function(n){return t.watchApartmentData(e.row)}}},[n("span",[t._v(t._s(e.row.apartmentAddress))])])],1)]}}])}),t._v(" "),n("el-table-column",{attrs:{label:"报修文件",prop:"hasFile","column-key":"hasFile",width:"110px",align:"center","filter-multiple":!1,"filter-method":t.filterHasFile,filters:t.hasFileList,"filter-placement":"bottom-end"},scopedSlots:t._u([{key:"default",fn:function(e){return[n("el-tooltip",{staticClass:"item",attrs:{effect:"light",content:0===e.row.hasfile?"":"点击查看报修文件",placement:"top"}},[n("el-button",{attrs:{disabled:1!==e.row.hasfile,type:0===e.row.hasfile?"info":"primary",size:"mini","close-transition":""},on:{click:function(n){return t.watchFileData(e.row)}}},[t._v(t._s(1===e.row.hasfile?"有文件":"无文件"))])],1)]}}])}),t._v(" "),n("el-table-column",{attrs:{label:"维修状态",prop:"status","column-key":"status",width:"110px",align:"center","filter-method":t.filterStatus,filters:t.statusList,"filter-placement":"bottom-left"},scopedSlots:t._u([{key:"default",fn:function(e){return[t.checkPermission(["售后人员"])?n("el-tooltip",{staticClass:"item",attrs:{effect:"light",content:"点击更改修理状态",placement:"right"}},[n("el-button",{attrs:{type:"success",size:"mini","close-transition":""},nativeOn:{click:function(n){return t.changeRepairStatus(e.row)}}},[n("span",[t._v(t._s(e.row.status))])])],1):n("el-tag",[t._v(t._s(e.row.status))])]}}])}),t._v(" "),n("el-table-column",{attrs:{label:"创建时间",prop:"createtime",align:"center"},scopedSlots:t._u([{key:"default",fn:function(e){return[n("span",[t._v(t._s(t._f("parseTime")(e.row.createtime)))])]}}])}),t._v(" "),n("el-table-column",{attrs:{label:"更新时间",prop:"conductTime",align:"center"},scopedSlots:t._u([{key:"default",fn:function(e){return[n("span",[t._v(t._s(t._f("parseTime")(e.row.conductTime)))])]}}])})],1),t._v(" "),n("pagination",{directives:[{name:"show",rawName:"v-show",value:t.total>0,expression:"total>0"}],attrs:{total:t.total,page:t.listQuery.page,limit:t.listQuery.limit},on:{"update:page":function(e){return t.$set(t.listQuery,"page",e)},"update:limit":function(e){return t.$set(t.listQuery,"limit",e)},pagination:t.getList}}),t._v(" "),n("el-dialog",{attrs:{title:"更改维修状态",visible:t.dialogStatusVisible},on:{"update:visible":function(e){t.dialogStatusVisible=e}}},[n("el-form",{ref:"userForm",attrs:{model:t.temp,"label-position":"center","label-width":"37%"}},[n("el-form-item",{attrs:{label:"请选择状态:",prop:"status"}},[n("el-select",{attrs:{placeholder:"请选择状态"},model:{value:t.temp.status,callback:function(e){t.$set(t.temp,"status",e)},expression:"temp.status"}},t._l(t.statusList,function(e){return n("el-option",{key:e.text,attrs:{label:e.value,value:e.value}},[n("span",{staticStyle:{float:"left"}},[t._v(t._s(e.value))])])}),1)],1)],1),t._v(" "),n("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[n("el-button",{on:{click:function(e){t.dialogStatusVisible=!1}}},[t._v("\n        取消\n      ")]),t._v(" "),n("el-button",{attrs:{disabled:t.confirmUpdate,type:"primary"},on:{click:function(e){return t.updateRepairStatusData()}}},[t._v("\n        确认修改\n      ")])],1)],1),t._v(" "),n("el-dialog",{attrs:{title:t.textMap[t.dialogStatus],visible:t.dialogUserVisible},on:{"update:visible":function(e){t.dialogUserVisible=e}}},[null!=t.userinfo?n("el-table",{directives:[{name:"loading",rawName:"v-loading",value:t.userLoading,expression:"userLoading"}],attrs:{data:[t.userinfo],border:"",fit:"","highlight-current-row":""}},[n("el-table-column",{attrs:{type:"expand"},scopedSlots:t._u([{key:"default",fn:function(e){return[n("el-form",{staticClass:"demo-table-expand",attrs:{"label-position":"left",inline:""}},[n("el-form-item",{attrs:{label:"租户ID"}},[n("span",[t._v(t._s(e.row.id))])]),t._v(" "),n("el-form-item",{attrs:{label:"租户姓名"}},[n("span",[t._v(t._s(e.row.name))])]),t._v(" "),n("el-form-item",{attrs:{label:"联系电话"}},[n("span",[t._v(t._s(e.row.phone))])]),t._v(" "),n("el-form-item",{attrs:{label:"租户性别"}},[n("span",[t._v(t._s(0===e.row.sex?"女":"男"))])]),t._v(" "),n("el-form-item",{attrs:{label:"租住状态"}},[n("span",[t._v(t._s(0===e.row.isLive?"未入住":"已入住"))])])],1)]}}],null,!1,3689265847)}),t._v(" "),n("el-table-column",{attrs:{prop:"id",label:"租户ID"}}),t._v(" "),n("el-table-column",{attrs:{prop:"name",label:"租户姓名"}}),t._v(" "),n("el-table-column",{attrs:{prop:"phone",label:"租户手机号"}})],1):t._e(),t._v(" "),n("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[n("el-button",{on:{click:function(e){t.dialogUserVisible=!1}}},[t._v("\n        关闭\n      ")])],1)],1),t._v(" "),n("el-dialog",{attrs:{title:t.textMap[t.dialogStatus],visible:t.dialogApartmentVisible},on:{"update:visible":function(e){t.dialogApartmentVisible=e}}},[null!=t.apartmentinfo?n("el-table",{directives:[{name:"loading",rawName:"v-loading",value:t.apartmentLoading,expression:"apartmentLoading"}],attrs:{data:[t.apartmentinfo],border:"",fit:"","highlight-current-row":""}},[n("el-table-column",{attrs:{type:"expand"},scopedSlots:t._u([{key:"default",fn:function(e){return[n("el-form",{staticClass:"demo-table-expand",attrs:{"label-position":"left",inline:""}},[n("el-form-item",{attrs:{label:"户型"}},[n("el-tag",{attrs:{type:"info"}},[t._v(t._s(e.row.houseType))])],1),t._v(" "),n("el-form-item",{attrs:{label:"面积"}},[n("el-tag",[t._v(t._s(e.row.roomArea)+"m²")])],1),t._v(" "),n("el-form-item",{attrs:{label:"朝向"}},[n("el-tag",{attrs:{type:"success"}},[t._v(t._s(e.row.face))])],1),t._v(" "),n("el-form-item",{attrs:{label:"地址"}},[n("span",[t._v(t._s(e.row.address))])])],1)]}}],null,!1,2340987133)}),t._v(" "),n("el-table-column",{attrs:{prop:"houseType",label:"户型"}}),t._v(" "),n("el-table-column",{attrs:{prop:"roomArea",label:"面积(平方米)"}}),t._v(" "),n("el-table-column",{attrs:{prop:"face",label:"朝向"}})],1):t._e(),t._v(" "),n("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[n("el-button",{on:{click:function(e){t.dialogApartmentVisible=!1}}},[t._v("\n        关闭\n      ")])],1)],1),t._v(" "),n("el-dialog",{attrs:{title:t.textMap[t.dialogStatus],width:"40%",visible:t.dialogFileVisible},on:{"update:visible":function(e){t.dialogFileVisible=e}}},[n("viewer",{ref:"viewer",staticClass:"viewer",attrs:{images:t.images},on:{inited:t.inited}},t._l(t.images,function(t){return n("img",{key:t,staticClass:"avatar",attrs:{src:t}})}),0),t._v(" "),n("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[n("el-button",{on:{click:function(e){t.dialogFileVisible=!1}}},[t._v("\n        关闭\n      ")])],1)],1)],1)},a=[],r=(n("ac4d"),n("8a81"),n("ac6a"),n("db72")),o=n("0adb"),s=n("7a07"),l=n("4f3d"),u=n("6724"),c=n("ed08"),d=n("e350"),f=n("4381"),p=n("333d"),m=n("2f62"),h={name:"ComplexTable",components:{Pagination:p["a"]},directives:{waves:u["a"],permission:f["a"]},data:function(){return{hasFileList:[{text:"有文件",value:1},{text:"无文件",value:0}],statusList:[{text:"进行中",value:"进行中"},{text:"已完成",value:"已完成"}],createtimeLimit:null,updatetimeLimit:null,userListLoading:!1,confirmUpdate:!1,images:[],userinfo:{id:null,name:null,sex:null,phone:"",idNumber:"",idType:"",isLive:null},apartmentinfo:{id:null,userid:null,roomArea:"",houseType:"",face:"",price:"",pattern:"",address:"",status:0},tableKey:0,list:null,total:1,listLoading:!1,listQuery:{page:1,limit:10,username:null,createTimeBefore:null,createTimeAfter:null,conductTimeBefore:null,conductTimeAfter:null,hasFileSet:null,statusSet:null,repairId:-1,userid:0},temp:{id:null,userid:null,username:"",apartmentid:"",apartmentAddress:"",status:"",content:"",createtime:"",conductTime:"",hasFile:null},dialogFormVisible:!1,dialogStatus:"",textMap:{userWatch:"查看租户信息",apartmentWatch:"查看公寓租户信息",fileWatch:"查看报修文件"},userLoading:!1,dialogUserVisible:!1,apartmentLoading:!1,dialogApartmentVisible:!1,dialogStatusVisible:!1,dialogFileVisible:!1,downloadLoading:!1,pickerOptions:{shortcuts:[{text:"最近一天",onClick:function(t){var e=new Date,n=new Date;n.setTime(n.getTime()-864e5),t.$emit("pick",[n,e])}},{text:"最近一周",onClick:function(t){var e=new Date,n=new Date;n.setTime(n.getTime()-6048e5),t.$emit("pick",[n,e])}},{text:"最近一个月",onClick:function(t){var e=new Date,n=new Date;n.setTime(n.getTime()-2592e6),t.$emit("pick",[n,e])}},{text:"最近三个月",onClick:function(t){var e=new Date,n=new Date;n.setTime(n.getTime()-7776e6),t.$emit("pick",[n,e])}}]}}},computed:Object(r["a"])({},Object(m["b"])(["userInfo"])),created:function(){this.getList()},methods:{checkPermission:d["a"],inited:function(t){this.$viewer=t},getList:function(){var t=this;this.listLoading=!0,this.checkPermission(["售后人员"])&&(this.listQuery.repairId=this.userInfo.repairId),this.checkPermission(["租户"])&&(this.listQuery.userid=this.userInfo.id),Object(o["c"])(this.listQuery).then(function(e){t.list=e.data.records,t.total=e.data.total,t.listLoading=!1})},handleFilter:function(){this.listQuery.page=1,this.getList()},resolveCreateLimit:function(){null===this.createtimeLimit?(this.listQuery.createTimeBefore=null,this.listQuery.createTimeAfter=null):(this.listQuery.createTimeBefore=Object(c["b"])(this.createtimeLimit[0]),this.listQuery.createTimeAfter=Object(c["b"])(this.createtimeLimit[1]))},resolveUpdateLimit:function(){null===this.updatetimeLimit?(this.listQuery.conductTimeBefore=null,this.listQuery.conductTimeAfter=null):(this.listQuery.conductTimeBefore=Object(c["b"])(this.updatetimeLimit[0]),this.listQuery.conductTimeAfter=Object(c["b"])(this.updatetimeLimit[1]))},changeRepairStatus:function(t){this.temp=Object.assign({},t),this.dialogStatusVisible=!0},updateRepairStatusData:function(){var t=this;this.confirmUpdate||(this.confirmUpdate=!0,Object(o["g"])(this.temp).then(function(e){var n=e.data;if(n){var i=!0,a=!1,r=void 0;try{for(var o,s=t.list[Symbol.iterator]();!(i=(o=s.next()).done);i=!0){var l=o.value;if(l.id===t.temp.id){var u=t.list.indexOf(l);t.temp.conductTime=new Date,t.list.splice(u,1,t.temp);break}}}catch(c){a=!0,r=c}finally{try{i||null==s.return||s.return()}finally{if(a)throw r}}t.dialogStatusVisible=!1,t.$notify({title:"成功",message:"更新成功",type:"success",duration:2e3})}else t.$notify({title:"失败",message:"更新失败",type:"error",duration:2e3})}),this.confirmUpdate=!1)},watchUserData:function(t){var e=this;this.userLoading=!0,this.dialogStatus="userWatch",this.dialogUserVisible=!0,Object(l["f"])(t.userid).then(function(t){e.userinfo=t.data,e.userLoading=!1})},watchApartmentData:function(t){var e=this;this.apartmentLoading=!0,this.dialogStatus="apartmentWatch",this.dialogApartmentVisible=!0,Object(s["a"])(t.apartmentid).then(function(t){e.apartmentinfo=t.data,e.apartmentLoading=!1})},watchFileData:function(t){var e=this;if(t.id===this.temp.id&&this.images.length>0)return this.dialogStatus="fileWatch",void(this.dialogFileVisible=!0);this.images=[],this.dialogStatus="fileWatch",this.temp=Object.assign({},t),Object(o["e"])(this.temp.id).then(function(t){var n=t.data,i=!0,a=!1,r=void 0;try{for(var o,s=n[Symbol.iterator]();!(i=(o=s.next()).done);i=!0){var l=o.value;e.images.unshift(l.resource)}}catch(u){a=!0,r=u}finally{try{i||null==s.return||s.return()}finally{if(a)throw r}}}),this.dialogFileVisible=!0},formatJson:function(t,e){return e.map(function(e){return t.map(function(t){return"timestamp"===t?Object(c["b"])(e[t]):e[t]})})},filterHasFile:function(t,e){return e.hasFile===t},filterStatus:function(t,e){return e.status===t},filterHanderChange:function(t){if(void 0!==t.hasFile)if(t.hasFile.length>=1)for(var e in this.listQuery.hasFileSet=[],t.hasFile)this.listQuery.hasFileSet.push(t.hasFile[e]);else this.listQuery.hasFileSet=null;else if(void 0!==t.status)if(t.status.length>=1)for(var n in this.listQuery.statusSet=[],t.status)this.listQuery.statusSet.push(t.status[n]);else this.listQuery.statusSet=null;this.getList()}}},b=h,g=(n("e8df"),n("8e3a"),n("2877")),v=Object(g["a"])(b,i,a,!1,null,"a7a877c4",null);e["default"]=v.exports},ac4d:function(t,e,n){n("3a72")("asyncIterator")},b2e8:function(t,e,n){},d4c0:function(t,e,n){var i=n("0d58"),a=n("2621"),r=n("52a7");t.exports=function(t){var e=i(t),n=a.f;if(n){var o,s=n(t),l=r.f,u=0;while(s.length>u)l.call(t,o=s[u++])&&e.push(o)}return e}},e350:function(t,e,n){"use strict";n.d(e,"a",function(){return a});n("6762"),n("2fdb");var i=n("4360");function a(t){if(t&&t instanceof Array&&t.length>0){var e=i["a"].getters&&i["a"].getters.roles,n=t,a=e.some(function(t){return n.includes(t)});return!!a}return console.error("need roles! Like v-permission=\"['admin','editor']\""),!1}},e8df:function(t,e,n){"use strict";var i=n("b2e8"),a=n.n(i);a.a},f77d:function(t,e,n){"use strict";var i=n("6017"),a=n.n(i);a.a}}]);