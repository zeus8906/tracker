<template>
  <el-card border="solid" width="120">
    <div class="el-card__header">
      <span>Current Tax Base: {{ this.taxBase.current }}%</span>
    </div>
    <div class="el-card__body">
      <el-form :model="taxBase">
        <el-form-item label="New Tax Base">
          <el-input-number :precision="1" :min="0" :max="100" v-model="taxBase.new"></el-input-number>
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
      'taxBase': {
        'current': 0,
        'new': 0
      }
    }
  },
  mounted () {
    axios.get('/api/args/tax_base').then(response => { this.taxBase.current = response.data })
  },
  methods: {
    applyNewTaxBase () {
      axios.put('/api/args/tax_base/' + this.taxBase.new).then(response => {
        this.taxBase.current = response.data
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
