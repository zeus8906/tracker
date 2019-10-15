<template>
  <el-dialog width="60%" center :visible="isRsuSellVisible" @close="close">
    <el-row :gutter="20">
      <el-col :span="20">
        <el-form :model="transaction">
          <el-form-item >
            <el-input-number v-model="transaction.count" :min="0" label="Count"></el-input-number>
            <el-input-number v-model="transaction.value" :min="0" :precision="2" label="Value"></el-input-number>
            <el-input-number v-model="transaction.usdhuf" :min="0" :precision="2" label="USD-HUF"></el-input-number>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
    <span slot="footer" class="dialog-footer">
      <el-button @click="cancelTransaction">Cancel</el-button>
      <el-button type="primary" @click="submitTransaction">Confirm</el-button>
    </span>
  </el-dialog>
</template>

<script>
export default {
  name: 'RSUSell',
  data () {
    return {
      transaction: {
        'count': 0,
        'value': 0.00,
        'usdhuf': 0.00,
        'items': []
      }
    }
  },
  computed: {
    isRsuSellVisible () {
      return this.$store.getters['RSUStore/isRsuSellVisible']
    }
  },
  methods: {
    submitTransaction () {
      this.close()
    },
    cancelTransaction () {
      this.close()
    },
    close () {
      this.$store.commit('RSUStore/setRsuSellVisible', false)
    }
  }
}
</script>

<style scoped>

</style>
