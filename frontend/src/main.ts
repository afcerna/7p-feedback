import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
import FeedbackForm from './components/feedback/feedbackForm.vue'
import FeedbackList from './components/feedback/feedbackList.vue'
import FeedbackEntry from './components/feedback/feedbackEntry.vue'

const app = createApp(App)
app.component('FeedbackForm', FeedbackForm)
app.component('FeedbackList', FeedbackList)
app.component('FeedbackEntry', FeedbackEntry)
app.mount('#app')
