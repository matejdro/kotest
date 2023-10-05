"use strict";(self.webpackChunkkotestdocs=self.webpackChunkkotestdocs||[]).push([[46112],{3905:(t,e,r)=>{r.d(e,{Zo:()=>c,kt:()=>m});var n=r(67294);function o(t,e,r){return e in t?Object.defineProperty(t,e,{value:r,enumerable:!0,configurable:!0,writable:!0}):t[e]=r,t}function s(t,e){var r=Object.keys(t);if(Object.getOwnPropertySymbols){var n=Object.getOwnPropertySymbols(t);e&&(n=n.filter((function(e){return Object.getOwnPropertyDescriptor(t,e).enumerable}))),r.push.apply(r,n)}return r}function a(t){for(var e=1;e<arguments.length;e++){var r=null!=arguments[e]?arguments[e]:{};e%2?s(Object(r),!0).forEach((function(e){o(t,e,r[e])})):Object.getOwnPropertyDescriptors?Object.defineProperties(t,Object.getOwnPropertyDescriptors(r)):s(Object(r)).forEach((function(e){Object.defineProperty(t,e,Object.getOwnPropertyDescriptor(r,e))}))}return t}function i(t,e){if(null==t)return{};var r,n,o=function(t,e){if(null==t)return{};var r,n,o={},s=Object.keys(t);for(n=0;n<s.length;n++)r=s[n],e.indexOf(r)>=0||(o[r]=t[r]);return o}(t,e);if(Object.getOwnPropertySymbols){var s=Object.getOwnPropertySymbols(t);for(n=0;n<s.length;n++)r=s[n],e.indexOf(r)>=0||Object.prototype.propertyIsEnumerable.call(t,r)&&(o[r]=t[r])}return o}var u=n.createContext({}),l=function(t){var e=n.useContext(u),r=e;return t&&(r="function"==typeof t?t(e):a(a({},e),t)),r},c=function(t){var e=l(t.components);return n.createElement(u.Provider,{value:e},t.children)},p={inlineCode:"code",wrapper:function(t){var e=t.children;return n.createElement(n.Fragment,{},e)}},f=n.forwardRef((function(t,e){var r=t.components,o=t.mdxType,s=t.originalType,u=t.parentName,c=i(t,["components","mdxType","originalType","parentName"]),f=l(r),m=o,d=f["".concat(u,".").concat(m)]||f[m]||p[m]||s;return r?n.createElement(d,a(a({ref:e},c),{},{components:r})):n.createElement(d,a({ref:e},c))}));function m(t,e){var r=arguments,o=e&&e.mdxType;if("string"==typeof t||o){var s=r.length,a=new Array(s);a[0]=f;var i={};for(var u in e)hasOwnProperty.call(e,u)&&(i[u]=e[u]);i.originalType=t,i.mdxType="string"==typeof t?t:o,a[1]=i;for(var l=2;l<s;l++)a[l]=r[l];return n.createElement.apply(null,a)}return n.createElement.apply(null,r)}f.displayName="MDXCreateElement"},94507:(t,e,r)=>{r.r(e),r.d(e,{assets:()=>u,contentTitle:()=>a,default:()=>p,frontMatter:()=>s,metadata:()=>i,toc:()=>l});var n=r(87462),o=(r(67294),r(3905));const s={id:"test_output",title:"Test Output",sidebar_label:"Test Output",slug:"test_output.html"},a=void 0,i={unversionedId:"framework/test_output",id:"version-5.7/framework/test_output",title:"Test Output",description:"If you are running Kotest via Gradle's Junit Platform support, and if you are using a nested spec style, you",source:"@site/versioned_docs/version-5.7/framework/output.md",sourceDirName:"framework",slug:"/framework/test_output.html",permalink:"/docs/framework/test_output.html",draft:!1,editUrl:"https://github.com/kotest/kotest/blob/master/documentation/versioned_docs/version-5.7/framework/output.md",tags:[],version:"5.7",frontMatter:{id:"test_output",title:"Test Output",sidebar_label:"Test Output",slug:"test_output.html"},sidebar:"framework",previous:{title:"Test Factories",permalink:"/docs/framework/test-factories.html"},next:{title:"Test Timeouts",permalink:"/docs/framework/timeouts/test-timeouts.html"}},u={},l=[],c={toc:l};function p(t){let{components:e,...r}=t;return(0,o.kt)("wrapper",(0,n.Z)({},c,r,{components:e,mdxType:"MDXLayout"}),(0,o.kt)("p",null,"If you are running Kotest via Gradle's Junit Platform support, and if you are using a nested spec style, you\nwill notice that only the leaf test name is included in output and test reports. This is a limitation of gradle\nwhich is designed around class.method test frameworks."),(0,o.kt)("p",null,"Until such time that Gradle improves their test integration so that tests can be arbitrarily nested, Kotest\noffers a workaround by allowing you to specify ",(0,o.kt)("inlineCode",{parentName:"p"},"displayFullTestPath")," in ",(0,o.kt)("a",{parentName:"p",href:"/docs/framework/project-config.html"},"project configuration"),"."),(0,o.kt)("p",null,"When this setting is enabled, the test names will be the concatenation of the entire test path. So a test like this:"),(0,o.kt)("pre",null,(0,o.kt)("code",{parentName:"pre",className:"language-kotlin"},'class MyTests: DescribeSpec({\n  describe("describe 1"){\n    it("test 1"){}\n    it("test 2"){}\n  }\n})\n')),(0,o.kt)("p",null,"Will be output as"),(0,o.kt)("pre",null,(0,o.kt)("code",{parentName:"pre"},"MyTests. describe 1 - test 1\nMyTests. describe 1 - test 2\n")))}p.isMDXComponent=!0}}]);