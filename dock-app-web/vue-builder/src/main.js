import Vue from 'vue'
import multiselect from './components/multiselect.vue'
import clientTable from './components/clientTable.vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'font-awesome/css/font-awesome.min.css'
import lodash from 'lodash/lodash.min.js'
import popover from './vue-strap/src/Popover.vue'
import alert from './vue-strap/src/Alert.vue'
import editable from './components/editable.vue'
import jquery from 'jquery/dist/jquery.min.js'
Vue.component('multiselect',multiselect);
Vue.component('clientTable',clientTable);
Vue.component('popover', popover);
Vue.component('alert',alert);
Vue.component('editable',editable);

window.Vue = Vue;  //put on global namespace to use with specific page scripts
window._ = lodash;
window.$ = jquery;
