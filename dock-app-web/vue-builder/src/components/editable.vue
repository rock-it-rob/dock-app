<template lang="html">
  <span v-on:click="setTempVal">
  <popover effect="fade" placement="right" ref="popover">
    <span class="editable" v-on:mouseover="hovering=true" v-on:mouseout="hovering=false">
      <slot></slot>
      <i v-if="hovering" class="fa fa-pencil"></i>
      <span v-else class="placeholder">&nbsp;</span>
    </span>
    <div slot="content">
      <input type="text" v-model="tempVal" v-on:keyup.enter="save">
      <input type="button" v-on:click="save" value="Save" class="btn btn-primary">
    </div>
  </popover>
</span>
</template>

<script>
export default {
  props:{
    initial:'',
    onEdit: Function,
    refs:Array
  },
  data:function(){
    return {
      tempVal:'',
      hovering:false
    }
  },
  methods:{
    save:function(){
      this.$refs.popover.toggle();
      this.onEdit(this.tempVal, this.refs);
    },
    setTempVal:function(){
      this.tempVal = this.initial;
    }
  },
}
</script>

<style lang="css">
.editable{
  cursor:pointer;
}
.fa-pencil{
  color:#337ab7;
}
.placeholder{
  display:inline-block;
  width:10.5px;
}
</style>
