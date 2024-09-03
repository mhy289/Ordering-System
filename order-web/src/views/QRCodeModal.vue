<template>
  <el-dialog
    title="扫码支付"
    :visible.sync="visible"
    width="400px"
    center
    :close-on-click-modal="false"
    :close-on-press-escape="false"
    @close="handleClose"
  >
    <img
      src="@/assets/img/qr-code.png"
      alt="二维码"
      class="qr-code-image"
    />
  </el-dialog>
</template>
  
  <script>
export default {
  props: {
    visible: {
      type: Boolean,
      required: true
    }
  },
  methods: {
    handleClose () {
      this.$emit('update:visible', false);
    }
  }
}
  </script>
  
  <style scoped>
.qr-code-image {
  width: 100%;
  height: auto;
  display: block;
}
</style>
  