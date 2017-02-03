<template lang="html">
  <div v-on-clickaway="closeList">
	<button type="button" class="btn btn-default" v-on:click="toggleList">
		<span class="multiselect-selected-text">{{displayText}}</span>
		<b class="caret"></b>
	</button>
	<ul class="multiselect-container dropdown-menu" v-show="showList" >
		<li v-for="(item, index) in optionsList" v-on:click="toggleItem(item)" v-bind:class="{'active' : item.checked}">
			<a>&nbsp;<input type="checkbox" v-bind:checked="item.checked">&nbsp;
			{{item.label}}</a>
		</li>
	</ul>
</div>
</template>

<script>
import { mixin as clickaway } from 'vue-clickaway';
export default {
  mixins: [ clickaway ],
	props: {  //These will be passed in from the parent page
		options: Array,
		value: { required: true }
	},
	data:function(){
		return{
			showList:false,
			optionsList:[]
		}
	},
	computed:{
		displayText() {
			if(this.value.length === 0)
				return "None Selected"
			if(this.value.length < 4)
				return this.value.join(', ')
			else
				return this.value.length + ' selected'
		}
	},
	methods:{
		toggleList(){
			this.showList = !this.showList;
		},
    closeList(){
			if(this.showList)
        this.showList = false;
		},
		toggleItem(item){
			item.checked = !item.checked;
			this.buildValues();
		},
		buildValues(){
			var values = [];
			this.optionsList.forEach (el=>{
				if(el.checked)
					values.push(el.value);
			});
			this.$emit('input',values);
		}
	},
	created: function(){
		this.optionsList = this.options;
	}
}
</script>

<style lang="css">
span.multiselect-native-select {
    position: relative
}
span.multiselect-native-select select {
    border: 0!important;
    clip: rect(0 0 0 0)!important;
    height: 1px!important;
    margin: -1px -1px -1px -3px!important;
    overflow: hidden!important;
    padding: 0!important;
    position: absolute!important;
    width: 1px!important;
    left: 50%;
    top: 30px
}
div>.multiselect-container {
    top: auto;
    left: auto;
    display: block;
}
.multiselect-container>li>a {
    cursor: pointer
}

.multiselect-container .input-group {
    margin: 5px
}
.multiselect-container>li {
    padding: 0
}
.multiselect-container>li>a.multiselect-all label {
    font-weight: 700
}
.multiselect-container>li.multiselect-group label {
    margin: 0;
    padding: 3px 20px 3px 20px;
    height: 100%;
    font-weight: 700
}
.multiselect-container>li.multiselect-group-clickable label {
    cursor: pointer
}
.multiselect-container>li>a {
    padding: 0
}
.multiselect-container>li>a>label {
    margin: 0;
    height: 100%;
    cursor: pointer;
    font-weight: 400;
    padding: 3px 20px 3px 40px
}
.multiselect-container>li>a>label.radio,
.multiselect-container>li>a>label.checkbox {
    margin: 0
}
.multiselect-container>li>a>label>input[type=checkbox] {
    margin-bottom: 5px
}
.btn-group>.btn-group:nth-child(2)>.multiselect.btn {
    border-top-left-radius: 4px;
    border-bottom-left-radius: 4px
}
.form-inline .multiselect-container label.checkbox,
.form-inline .multiselect-container label.radio {
    padding: 3px 20px 3px 40px
}
.form-inline .multiselect-container li a label.checkbox input[type=checkbox],
.form-inline .multiselect-container li a label.radio input[type=radio] {
    margin-left: -20px;
    margin-right: 0
}
</style>
