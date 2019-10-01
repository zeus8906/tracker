export const state = {
  rsus: [],
  isAddFormVisible: false,
  newRSU: {
    count: 0
  }
}

export const getters = {
  getRsus: state => state.rsus,
  isAddFormVisible: state => state.isAddFormVisible,
  getNewRSU: state => state.newRSU
}

export const mutations = {
  addRsu (state, newRsu) {
    state.rsus.push(newRsu)
  },
  setRsus (state, rsuList) {
    state.rsus = rsuList
  },
  deleteRsu (state, rsuId) {
    state.rsus = state.rsus.filter(rsu => rsu.id !== parseInt(rsuId))
  },
  updateRsu (state, updated) {
    state.rsu = state.rsus.map(rsu => { return rsu.id === updated.id ? updated : rsu })
  },
  setAddFormVisible (state, visible) {
    state.isAddFormVisible = visible
  },
  clearNewRSU (state) {
    state.newRSU = {
      count: 0
    }
  },
  setNewRSU (state, update) {
    state.newRSU = update
  }
}
