import Vue from 'vue'
import moment from 'moment'

Vue.filter('dateFormatter', function (val) {
  return moment(String(val)).format('YYYY-MM-DD')
})

Vue.filter('dollarFormatter', function (val) {
  return '$' + val
})

Vue.filter('hufFormatter', function (val) {
  return val + ' Ft'
})

Vue.filter('currencyFormatter', function (value) {
  let val = (value / 1).toFixed(2).replace('.', ',')
  return val.toString().replace(/\B(?=(\d{3})+(?!\d))/g, '.')
})

Vue.filter('percentFormatter', function (val) {
  return val + '%'
})
