<template>
  <div
    v-show="value"
    v-bind:class="{
      'alert':		true,
      'alert-success':(type == 'success'),
      'alert-warning':(type == 'warning'),
      'alert-info':	(type == 'info'),
      'alert-danger':	(type == 'danger'),
      'top': 			(placement === 'top'),
      'top-right': 	(placement === 'top-right')
    }"
    transition="fade"
    v-bind:style="{width:width}"
    role="alert">
    <button v-show="dismissable" type="button" class="close"
      @click="hideAlert()">
      <span>&times;</span>
    </button>
    <slot></slot>
  </div>
</template>

<script>
export default {
  props: {
    type: {
      type: String
    },
    dismissable: {
      type: Boolean,
      default: false
    },
    value: {
      type: Boolean,
      default: true,
      required:true
    },
    duration: {
      type: Number,
      default: 0
    },
    width: {
      type: String
    },
    placement: {
      type: String
    }
  },
  methods:{
    hideAlert(){
      this.$emit('input',false);
    }
  },
  watch: {
    value (val) {
      if (this._timeout) clearTimeout(this._timeout)
      if (val && Boolean(this.duration)) {
        this._timeout = setTimeout(() => { this.hideAlert() }, this.duration)
      }
    }
  }
}
</script>

<style>
.fade-enter-active, .fade-leave-active {
  transition: opacity .3s ease;
}
.fade-enter,
.fade-leave-active {
  height: 0;
  opacity: 0;
}
.alert.top {
  position: fixed;
  top: 30px;
  margin: 0 auto;
  left: 0;
  right: 0;
  z-index: 1050;
}
.alert.top-right {
  position: fixed;
  top: 30px;
  right: 50px;
  z-index: 1050;
}
</style>
