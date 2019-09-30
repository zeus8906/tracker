export const state = {
  taxBase: 0
}

export const getters = {
  getTaxBase: state => state.taxBase
}

export const mutations = {
  setTaxBase (state, newTaxBase) {
    state.taxBase = newTaxBase
  }
}
