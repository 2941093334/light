<template>
  <div id="app">
    <el-container>
      <el-header>
        <h1>灯具管理系统</h1>
      </el-header>
      
      <el-main>
        <el-card style="margin-bottom: 20px">
          <el-form :inline="true" class="search-form">
            <el-form-item label="品牌">
              <el-input v-model="searchForm.brand" placeholder="请输入品牌" clearable></el-input>
            </el-form-item>
            <el-form-item label="型号">
              <el-input v-model="searchForm.model" placeholder="请输入型号" clearable></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="handleSearch">查询</el-button>
              <el-button @click="handleReset">重置</el-button>
            </el-form-item>
          </el-form>
        </el-card>
        
        <el-card>
          <div slot="header">
            <span>灯具列表</span>
            <el-button 
              style="float: right; padding: 3px 0; margin-right: 10px" 
              type="primary"
              size="small"
              @click="handleBatchDelete"
              :disabled="selectedIds.length === 0"
            >
              批量删除
            </el-button>
            <el-button 
              style="float: right; padding: 3px 0" 
              type="text"
              @click="showAddDialog"
            >
              添加灯具
            </el-button>
          </div>
          
          <el-table 
            :data="lampList" 
            stripe 
            style="width: 100%"
            @selection-change="handleSelectionChange"
          >
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column prop="id" label="ID" width="80"></el-table-column>
            <el-table-column prop="brand" label="品牌" width="120"></el-table-column>
            <el-table-column prop="model" label="型号" width="150"></el-table-column>
            <el-table-column prop="power" label="功率(瓦特)" width="120">
              <template slot-scope="scope">
                {{ scope.row.power }} W
              </template>
            </el-table-column>
            <el-table-column prop="colorTemperature" label="色温(开尔文)" width="150">
              <template slot-scope="scope">
                {{ scope.row.colorTemperature }} K
              </template>
            </el-table-column>
            <el-table-column label="操作">
              <template slot-scope="scope">
                <el-button size="mini" @click="handleEdit(scope.row)">编辑</el-button>
                <el-button size="mini" type="danger" @click="handleDelete(scope.row)">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-card>
        
        <el-dialog 
          :title="dialogTitle" 
          :visible.sync="dialogVisible"
          width="500px"
        >
          <el-form :model="lampForm" :rules="rules" ref="lampForm" label-width="100px">
            <el-form-item label="品牌" prop="brand">
              <el-input v-model="lampForm.brand" placeholder="请输入品牌"></el-input>
            </el-form-item>
            <el-form-item label="型号" prop="model">
              <el-input v-model="lampForm.model" placeholder="请输入型号"></el-input>
            </el-form-item>
            <el-form-item label="功率" prop="power">
              <el-input-number 
                v-model="lampForm.power" 
                :min="0" 
                :precision="2"
                placeholder="请输入功率"
                style="width: 100%"
              ></el-input-number>
            </el-form-item>
            <el-form-item label="色温" prop="colorTemperature">
              <el-input-number 
                v-model="lampForm.colorTemperature" 
                :min="0" 
                :max="10000"
                placeholder="请输入色温"
                style="width: 100%"
              ></el-input-number>
            </el-form-item>
          </el-form>
          <span slot="footer" class="dialog-footer">
            <el-button @click="dialogVisible = false">取消</el-button>
            <el-button type="primary" @click="handleSubmit">确定</el-button>
          </span>
        </el-dialog>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import axios from 'axios'

const api = axios.create({
  baseURL: 'http://localhost:7001/api/lamp',
  timeout: 5000,
  headers: {
    'Content-Type': 'application/json'
  }
})

export default {
  name: 'App',
  data() {
    return {
      lampList: [],
      selectedIds: [],
      searchForm: {
        brand: '',
        model: ''
      },
      dialogVisible: false,
      dialogTitle: '添加灯具',
      lampForm: {
        id: null,
        brand: '',
        model: '',
        power: 0,
        colorTemperature: 3000
      },
      rules: {
        brand: [
          { required: true, message: '请输入品牌', trigger: 'blur' }
        ],
        model: [
          { required: true, message: '请输入型号', trigger: 'blur' }
        ],
        power: [
          { required: true, message: '请输入功率', trigger: 'blur' }
        ],
        colorTemperature: [
          { required: true, message: '请输入色温', trigger: 'blur' }
        ]
      }
    }
  },
  created() {
    this.loadLamps()
  },
  methods: {
    async loadLamps() {
      try {
        const response = await api.get('')
        if (response.data.code === 200) {
          this.lampList = response.data.data || []
        } else {
          this.$message.error(response.data.message || '加载失败')
        }
      } catch (error) {
        console.error('加载灯具列表失败:', error)
        this.$message.error('加载灯具列表失败')
      }
    },
    async handleSearch() {
      try {
        const params = {}
        if (this.searchForm.brand) {
          params.brand = this.searchForm.brand
        }
        if (this.searchForm.model) {
          params.model = this.searchForm.model
        }
        
        const response = await api.get('/search', { params })
        if (response.data.code === 200) {
          this.lampList = response.data.data || []
          if (this.lampList.length === 0) {
            this.$message.info('未找到符合条件的数据')
          }
        } else {
          this.$message.error(response.data.message || '查询失败')
        }
      } catch (error) {
        console.error('查询失败:', error)
        this.$message.error('查询失败')
      }
    },
    handleReset() {
      this.searchForm = {
        brand: '',
        model: ''
      }
      this.loadLamps()
    },
    handleSelectionChange(selection) {
      this.selectedIds = selection.map(item => item.id)
    },
    showAddDialog() {
      this.dialogTitle = '添加灯具'
      this.lampForm = {
        id: null,
        brand: '',
        model: '',
        power: 0,
        colorTemperature: 3000
      }
      this.dialogVisible = true
      this.$nextTick(() => {
        this.$refs.lampForm && this.$refs.lampForm.clearValidate()
      })
    },
    handleEdit(row) {
      this.dialogTitle = '编辑灯具'
      this.lampForm = { ...row }
      this.dialogVisible = true
    },
    async handleDelete(row) {
      this.$confirm('确认删除该灯具吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async () => {
        try {
          const response = await api.delete('', {
            data: [row.id]
          })
          if (response.data.code === 200) {
            this.$message.success('删除成功')
            this.loadLamps()
          } else {
            this.$message.error(response.data.message || '删除失败')
          }
        } catch (error) {
          console.error('删除失败:', error)
          this.$message.error('删除失败')
        }
      }).catch(() => {})
    },
    async handleBatchDelete() {
      if (this.selectedIds.length === 0) {
        this.$message.warning('请选择要删除的灯具')
        return
      }
      this.$confirm(`确认删除选中的 ${this.selectedIds.length} 个灯具吗?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async () => {
        try {
          const response = await api.delete('', {
            data: this.selectedIds
          })
          if (response.data.code === 200) {
            this.$message.success('批量删除成功')
            this.selectedIds = []
            this.loadLamps()
          } else {
            this.$message.error(response.data.message || '删除失败')
          }
        } catch (error) {
          console.error('批量删除失败:', error)
          this.$message.error('批量删除失败')
        }
      }).catch(() => {})
    },
    handleSubmit() {
      this.$refs.lampForm.validate((valid) => {
        if (valid) {
          this.submitForm()
        }
      })
    },
    async submitForm() {
      try {
        let response
        if (this.lampForm.id) {
          response = await api.put('', this.lampForm)
        } else {
          response = await api.post('', this.lampForm)
        }
        
        if (response.data.code === 200) {
          this.$message.success(this.lampForm.id ? '更新成功' : '添加成功')
          this.dialogVisible = false
          this.loadLamps()
        } else {
          this.$message.error(response.data.message || '操作失败')
        }
      } catch (error) {
        console.error('操作失败:', error)
        this.$message.error('操作失败：' + (error.message || '未知错误'))
      }
    }
  }
}
</script>

<style lang="scss">
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  
  .el-header {
    background-color: #409EFF;
    color: white;
    display: flex;
    align-items: center;
    
    h1 {
      margin: 0;
      font-size: 24px;
    }
  }
  
  .el-main {
    padding: 20px;
    background-color: #f5f7fa;
  }
}
</style>
