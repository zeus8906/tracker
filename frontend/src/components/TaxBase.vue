<template>
  <el-card border="solid" width="120">
    <div class="el-card__header">
      <span>Current Tax Base: {{ this.taxBase | percentFormatter }}</span>
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
import repo from '../repositories/TaxBaseRepository'
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
      return this.$store.getters['TaxBaseStore/getTaxBase']
    }
  },
  async mounted () {
    const taxBase = await repo.get().catch(error => {
      this.$message(error.message)
    })
    this.$store.commit('TaxBaseStore/setTaxBase', taxBase)
    this.form.newTaxBase = taxBase
  },
  methods: {
    async applyNewTaxBase () {
      const updated = await repo.update(this.form.newTaxBase).catch(
        error => this.$message(error.message)
      )
      this.$store.commit('TaxBaseStore/setTaxBase', updated)
      this.form.newTaxBase = this.taxBase
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
