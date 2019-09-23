<template>
  <el-card border="solid" width="120">
    <div class="el-card__header">
      <span>Current Tax Base: {{ this.taxBase }}%</span>
    </div>
    <div class="el-card__body">
      <el-form :model="form">
        <el-form-item label="New Tax Base">
          <el-input-number :precision="1" :min="0" :max="100" v-model="form.newTaxBase"></el-input-number>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="applyNewTaxBase">Apply</el-button>
        </el-form-item>
      </el-form>
    </div>
  </el-card>
</template>

<script>
import axios from 'axios'
export default {
  'name': 'TaxBase',
  data () {
    return {
      'form': {
        'newTaxBase': 0
      }
    }
  },
  computed: {
    taxBase () {
      return this.$store.getters.getTaxBase
    }
  },
  async mounted () {
    await this.$store.dispatch('loadTaxBase')
    this.$set(this.form, 'newTaxBase', this.taxBase)
  },
  methods: {
    applyNewTaxBase () {
      axios.put('/api/args/tax_base/' + this.form.newTaxBase)
        .then(response => response.data)
        .then(updated => {
          this.$store.commit('setTaxBase', updated)
          this.form.newTaxBase = this.taxBase
        })
    }
  }
}
</script>
<style>
.el-card{
  width: 400px;
  margin: 20px;
}
</style>
