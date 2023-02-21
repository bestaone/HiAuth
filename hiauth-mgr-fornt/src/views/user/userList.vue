<template>
  <div class="app-container">

    <div class="filter-container">
      <el-input v-model="userTable.queryParams.name" placeholder="姓名" style="width: 200px;" class="filter-item" @keyup.enter.native="handleFilter"/>
      <el-input v-model="userTable.queryParams.username" placeholder="用户名" style="width: 200px;" class="filter-item" @keyup.enter.native="handleFilter"/>
      <el-input v-model="userTable.queryParams.tel" placeholder="手机号" style="width: 200px;" class="filter-item" @keyup.enter.native="handleFilter"/>
      <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">{{ $t('table.search') }}</el-button>
      <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit" @click="handleCreate">{{ $t('table.add') }}</el-button>
      <el-button v-waves :loading="downloadLoading" class="filter-item" type="primary" icon="el-icon-download" @click="handleDownload">{{ $t('table.export') }}</el-button>
    </div>

    <el-table
      v-loading="userTable.listLoading"
      :key="userTable.tableKey"
      :data="userTable.list"
      border
      fit
      highlight-current-row
      style="width: 100%;">
      <el-table-column label="序号" width="50" align="center">
        <template slot-scope="scope">
          <span>{{ scope.$index + (userTable.queryParams.pageNum - 1) * userTable.queryParams.pageSize + 1 }}</span>
        </template>
      </el-table-column>
      <el-table-column label="姓名" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column label="用户名">
        <template slot-scope="scope">
          <span>{{ scope.row.username }}</span>
        </template>
      </el-table-column>
      <el-table-column label="手机号" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.tel }}</span>
        </template>
      </el-table-column>
      <el-table-column label="性别" width="50" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.gender | genderFilter }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" width="150">
        <template slot-scope="scope">
          <span>{{ scope.row.createTime | parseTime('{y}-{m}-{d} {h}:{i}') }}</span>
        </template>
      </el-table-column>
      <el-table-column v-if="checkPermission(['ROLE_ADMIN'])" label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="handleUpdate(scope.row)">{{ $t('table.edit') }}</el-button>
          <el-button v-if="scope.row.status!='deleted'" size="mini" type="danger" @click="handleModifyStatus(scope.row,'deleted')">{{ $t('table.delete') }}
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="userTable.total>0" :total="userTable.total" :page.sync="userTable.queryParams.pageNum" :limit.sync="userTable.queryParams.pageSize" @pagination="getUserPage" />

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :rules="rules" :model="user" label-position="left" label-width="70px" style="width: 400px; margin-left:50px;">
        <el-form-item label="姓名" prop="name" placeholder="输入姓名">
          <el-input v-model="user.name" class="filter-item"/>
        </el-form-item>
        <el-form-item label="账号" prop="username" placeholder="输入账号">
          <el-input v-model="user.username" class="filter-item"/>
        </el-form-item>
        <el-form-item label="手机号" prop="tel" placeholder="输入手机号">
          <el-input v-model="user.tel" class="filter-item"/>
        </el-form-item>
        <el-form-item label="密码" prop="password" placeholder="输入密码">
          <el-input v-model="user.password" type="password" class="filter-item"/>
        </el-form-item>
        <el-form-item label="性别" prop="gender">
          <el-select v-model="user.gender" placeholder="选择性别">
            <el-option v-for="item in genderOptions" :key="item.key" :label="item.name" :value="item.key"/>
          </el-select>
        </el-form-item>
        <el-form-item label="角色" prop="roleIds">
          <el-select v-model="user.roleIds" placeholder="选择角色" multiple>
            <el-option v-for="item in roleOptions" :key="item.id" :label="item.name" :value="item.id"/>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">{{ $t('table.cancel') }}</el-button>
        <el-button type="primary" @click="dialogStatus==='create'?createData():updateData()">{{ $t('table.confirm') }}</el-button>
      </div>
    </el-dialog>

    <el-dialog :visible.sync="dialogPvVisible" title="Reading statistics">
      <el-table :data="pvData" border fit highlight-current-row style="width: 100%">
        <el-table-column prop="key" label="Channel"/>
        <el-table-column prop="pv" label="Pv"/>
      </el-table>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogPvVisible = false">{{ $t('table.confirm') }}</el-button>
      </span>
    </el-dialog>

  </div>
</template>

<script>

import checkPermission from '@/utils/permission' // 权限判断函数
import { fetchPv } from '@/api/article'
import { queryUser, savaUser } from '@/api/user'
import { getRoleByUserId } from '@/api/role'
import waves from '@/directive/waves'
import { parseTime } from '@/utils'
import Pagination from '@/components/Pagination'

const genderOptions = [
  { key: 'MALE', name: '男' },
  { key: 'FEMALE', name: '女' },
  { key: 'UNKNOWN', name: '未知' }
]

const roleOptions = [
  { id: 1, name: '管理员' },
  { id: 2, name: '普通用户' }
]

export default {
  name: 'UserTable',
  components: { Pagination },
  directives: { waves },
  filters: {
    genderFilter(gender) {
      const genderMap = {
        MALE: '男',
        FEMALE: '女',
        UNKNOWN: '未知'
      }
      return genderMap[gender]
    }
  },
  data() {
    return {
      userTable: {
        tableKey: 0,
        list: null,
        total: 0,
        listLoading: true,
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          name: undefined,
          username: undefined,
          tel: undefined,
          sort: '+id'
        }
      },
      genderOptions,
      roleOptions,
      user: {
        id: undefined,
        name: '',
        username: '',
        tel: '',
        password: '',
        gender: '',
        createTime: undefined,
        roleIds: []
      },
      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: 'Edit',
        create: 'Create'
      },
      dialogPvVisible: false,
      pvData: [],
      rules: {
        name: [
          { required: true, message: '姓名不能为空', trigger: 'blur' },
          { type: 'string', min: 3, message: '最小3个字符字符', trigger: 'blur' },
          { type: 'string', max: 10, message: '最大10个字符字符', trigger: 'blur' }
        ],
        username: [
          { required: true, message: '账号不能为空', trigger: 'blur' },
          { type: 'string', min: 5, message: '最小5个字符字符', trigger: 'blur' },
          { type: 'string', max: 20, message: '最大20个字符字符', trigger: 'blur' }
        ],
        tel: [
          { required: true, message: '手机号码不能为空', trigger: 'change' }
        ],
        gender: [
          { required: true, message: '请选择性别', trigger: 'change' }
        ],
        password: [
          { required: true, message: '密码不能为空', trigger: 'change' },
          { type: 'string', min: 5, message: '最小5个字符字符', trigger: 'blur' },
          { type: 'string', max: 20, message: '最大20个字符字符', trigger: 'blur' }
        ],
        role: [
          { required: true, message: '请设置角色', trigger: 'change' }
        ]
      },
      downloadLoading: false
    }
  },
  created() {
    this.getUserPage()
  },
  methods: {
    checkPermission,
    getUserPage() {
      this.userTable.listLoading = true
      queryUser(this.userTable.queryParams).then(response => {
        this.userTable.list = response.data.data.list
        this.userTable.total = response.data.data.total

        // Just to simulate the time of the request
        setTimeout(() => {
          this.userTable.listLoading = false
        }, 1.5 * 1000)
      })
    },
    handleFilter() {
      this.userTable.queryParams.pageNum = 1
      // this.getList()
    },
    resetUser() {
      this.user = {
        id: undefined,
        name: '',
        username: '',
        tel: '',
        password: '',
        gender: '',
        createTime: undefined,
        roleIds: []
      }
    },
    handleCreate() {
      this.resetUser()
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    createData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          const userData = Object.assign({}, this.user)
          savaUser(undefined, userData).then(response => {
            this.user.id = response.data.data
            this.user.createTime = new Date()
            this.userTable.list.unshift(this.user)
            this.dialogFormVisible = false
            this.$notify({
              title: '成功',
              message: '创建成功',
              type: 'success',
              duration: 2000
            })
          })
        }
      })
    },
    handleUpdate(row) {
      // this.user = Object.assign({}, row)
      this.user.id = row.id
      this.user.name = row.name
      this.user.username = row.username
      this.user.tel = row.tel
      this.user.password = row.password
      this.user.gender = row.gender
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
        getRoleByUserId(this.user.id).then(response => {
          var roleIds = []
          var roles = response.data.data
          if (roles && roles.length > 0) {
            roles.forEach(function(role, i) {
              roleIds.push(role.id)
            })
          }
          this.user.roleIds = roleIds
        })
      })
    },
    updateData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          const userData = Object.assign({}, this.user)
          savaUser(this.user.id, userData).then(response => {
            for (const v of this.userTable.list) {
              if (v.id === this.user.id) {
                const index = this.userTable.list.indexOf(v)
                this.userTable.list.splice(index, 1, this.user)
                break
              }
            }
            this.dialogFormVisible = false
            this.$notify({
              title: '成功',
              message: '更新成功',
              type: 'success',
              duration: 2000
            })
          })
        }
      })
    },
    handleDelete(row) {
      this.$notify({
        title: '成功',
        message: '删除成功',
        type: 'success',
        duration: 2000
      })
      const index = this.userTable.list.indexOf(row)
      this.userTable.list.splice(index, 1)
    },
    handleFetchPv(pv) {
      fetchPv(pv).then(response => {
        this.pvData = response.data.pvData
        this.dialogPvVisible = true
      })
    },
    handleDownload() {
      this.downloadLoading = true
      import('@/vendor/Export2Excel').then(excel => {
        const tHeader = ['timestamp', 'title', 'type', 'importance', 'status']
        const filterVal = ['timestamp', 'title', 'type', 'importance', 'status']
        const data = this.formatJson(filterVal, this.userTable.list)
        excel.export_json_to_excel({
          header: tHeader,
          data,
          filename: 'table-list'
        })
        this.downloadLoading = false
      })
    },
    formatJson(filterVal, jsonData) {
      return jsonData.map(v => filterVal.map(j => {
        if (j === 'timestamp') {
          return parseTime(v[j])
        } else {
          return v[j]
        }
      }))
    }
  }
}
</script>
