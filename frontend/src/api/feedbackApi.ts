import axios from 'axios'
import type { Feedback, CreateFeedbackRequest } from '../types/feedback'

const api = axios.create({
  baseURL: 'http://localhost:8080',
})

export async function createFeedback(data: CreateFeedbackRequest): Promise<Feedback> {
  const { data: feedback } = await api.post<Feedback>('/feedback', data)
  return feedback
}

export async function getAllFeedback(): Promise<Feedback[]> {
  const { data } = await api.get<Feedback[]>('/feedback')
  return data
}

export async function markHelpful(id: string): Promise<Feedback> {
  const { data } = await api.put<Feedback>(`/feedback/${id}/helpful`)
  return data
}
