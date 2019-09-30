<template>
  <div id="main">
    <el-container>
      <el-header><h1>RSU Tracker</h1></el-header>
      <el-main>
        <el-row :gutter="10">
          <el-col :span="14">
            <h4>Given RSUs</h4>
            <RSUList></RSUList>
          </el-col>
          <el-col :span="10">
            <h4>Tax types</h4>
            <TaxType></TaxType>
          </el-col>
        </el-row>
        <el-row :gutter="10">
          <el-col :span="10">
            <TaxBase></TaxBase>
          </el-col>
          <el-col :span="14">
            <h4>Taxes</h4>
            <TaxList></TaxList>
          </el-col>
        </el-row>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import TaxType from './TaxType'
import RSUList from './RSU/RSUList'
import TaxBase from './TaxBase'
import TaxList from './TaxList'

export default {
  name: 'Main',
  components: {
    RSUList,
    TaxType,
    TaxBase,
    TaxList
  },
  errorCaptured (err, vm, info) {
    this.$message.warning(err.message)
  }
}
</script>

<style>
.el-row {
  margin-bottom: 20px;
  &:last-child {
    margin-bottom: 0;
  }
}
h4 {
  margin: 0px;
}

div > .el-button{
margin-top: 5px;
float: left;
}
</style>
